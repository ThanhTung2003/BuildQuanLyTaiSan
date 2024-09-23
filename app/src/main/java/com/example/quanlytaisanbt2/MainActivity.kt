package com.example.quanlytaisanbt2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.quanlytaisanbt2.UI.formatMoney
import com.example.quanlytaisanbt2.databinding.ActivityMainBinding
import com.example.quanlytaisanbt2.layout.GridLayout
import com.example.quanlytaisanbt2.layout.ResultsScreen
import com.example.quanlytaisanbt2.layout_kt.RecyclerViewLayout
import com.example.quanlytaisanbt2.layout_kt.RecyclerViewPro

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(BT2, "onCreate")

        val personList = mutableListOf<Person>()
        val assetList = mutableListOf<Asset>()
        val currentPersonAssets = mutableListOf<Asset>()

        val peopleAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, personList.map { it.getPersonName() })
        val assetAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, assetList.map { it.getInfo() })

        binding.peopleListView.adapter = peopleAdapter
        binding.assetListView.adapter = assetAdapter

        binding.layoutPeople.visibility = View.VISIBLE
        binding.layoutAssets.visibility = View.GONE
        Log.d(BT2, "View mặc định")

        binding.radioGroupPeopleAndAsset.setOnCheckedChangeListener { _, checkid ->
            when (checkid) {
                R.id.radiobuttonPeople -> {
                    binding.layoutPeople.visibility = View.VISIBLE
                    binding.layoutAssets.visibility = View.GONE
                    Log.d(BT2, PERSONVIEW)

                }
                R.id.radiobuttonAsset -> {
                    binding.layoutPeople.visibility = View.GONE
                    binding.layoutAssets.visibility = View.VISIBLE
                    Log.d(BT2, ASSETVIEW)

                }
            }
        }

        fun updateTextListAssets() {
            val assetsInfo = currentPersonAssets.joinToString(", ") { it.getName() }
            binding.textListAssets.text = assetsInfo
        }

        fun addAssetToCurrentPerson(asset: Asset) {
            currentPersonAssets.add(asset)
            updateTextListAssets()
        }

        fun updatePeopleAdapter() {
            peopleAdapter.clear()
            peopleAdapter.addAll(personList.map { it.getFullInfo() })
            peopleAdapter.notifyDataSetChanged()
        }

        fun savePerson(name: String) {
            if (name.isNotEmpty() && currentPersonAssets.isNotEmpty()) {
                val person = Person(name)

                currentPersonAssets.forEach { asset ->
                    person.addAsset(asset)
                }
                personList.add(person)
                updatePeopleAdapter()
                currentPersonAssets.clear()
                updateTextListAssets()
                Log.d(BT2, "Thêm $name thành công")
            } else {
                Log.d(BT2, "$name chưa thêm tài sản")
            }
        }

        fun updateAssetsListView() {
            val assetsInfo = assetList.map { asset -> "${asset.getName()} - ${asset.value.formatMoney()}" }
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, assetsInfo)
            binding.assetListView.adapter = adapter
        }

        binding.buttonAddAsset.setOnClickListener {
            val assetName = binding.editTextAsset.text.toString()
            val assetValue = binding.editTextValueAsset.text.toString().toLongOrNull()

            if (assetName.isNotEmpty() && assetValue != null) {
                val asset = Asset(assetName, assetValue)
                assetList.add(asset)
                assetAdapter.notifyDataSetChanged()
                addAssetToCurrentPerson(asset) // add tài sản vào người hiện tại
                binding.editTextAsset.text.clear()
                binding.editTextValueAsset.text.clear()
//                updateAssetsListView()
                Log.d(BT2, "Thêm $assetName thành công với giá: ${assetValue.formatMoney()}")
            } else {
                Log.d(BT2, "Chưa điền tên hoặc giá tài sản")
            }
        }

        binding.buttonAddPeople.setOnClickListener {
            val personName = binding.editTextPeople.text.toString()
            savePerson(personName)
            binding.editTextPeople.text.clear()
        }

            fun viewResult() {
            val builder = StringBuilder()
            Log.d(BT2, "xem kết quả")
            builder.append("- Thống kê danh sách đối tượng:\n")

            personList.forEach { person ->
                builder.append(person.getInfoStatistical()).append("\n")
            }

            assetList.forEach { asset ->
                builder.append(asset.getInfoStatistical()).append("\n")
            }

            Log.d(BT2, builder.toString())
        }

        binding.personResult.setOnClickListener {
            viewResult()
            val intentMain: Intent = Intent(this@MainActivity, ResultsScreen::class.java)
            startActivity(intentMain)
            Log.d(BT2, "Chuyển sang màn hình kết quả")
        }

        binding.assetResults.setOnClickListener {
            viewResult()
            val intentMain: Intent = Intent(this@MainActivity, ResultsScreen::class.java)
            startActivity(intentMain)
            Log.d(BT2, "Chuyển sang màn hình kết quả")
        }

        binding.buttonGridLayout.setOnClickListener {
            val intentMain: Intent = Intent(this@MainActivity, GridLayout::class.java)
            startActivity(intentMain)
            Log.d(BT2, "Chuyển sang GridLayout")
        }

        binding.buttonRecyclerView.setOnClickListener {
            val intentMain: Intent = Intent(this@MainActivity, RecyclerViewLayout::class.java)
            startActivity(intentMain)
            Log.d(BT2, "Chuyển sang RecyclerViewLayout")
        }

        binding.buttonRecyclerViewPro.setOnClickListener {
            val intentMain: Intent = Intent(this@MainActivity, RecyclerViewPro::class.java)
            startActivity(intentMain)
            Log.d(BT2, "Chuyển sang RecyclerViewPro")
        }

    }
    companion object {
        private const val BT2 = "baitap2"
        private const val PERSONVIEW = "View Con người"
        private const val ASSETVIEW = "View Tài sản"
    }
}
