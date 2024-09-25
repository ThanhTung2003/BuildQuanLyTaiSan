package com.example.quanlytaisanbt2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quanlytaisanbt2.Data.DataAsset
import com.example.quanlytaisanbt2.Data.DataPerson
import com.example.quanlytaisanbt2.UI.formatMoney
import com.example.quanlytaisanbt2.adapter.AssetAdapter
import com.example.quanlytaisanbt2.adapter.PersonAdapter
import com.example.quanlytaisanbt2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var personAdapter: PersonAdapter
    private lateinit var assetAdapter: AssetAdapter
    private val personList = mutableListOf<DataPerson>()
    private val selectedAssets = mutableListOf<DataAsset>()
    private val assetList = mutableListOf<DataAsset>()

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(BT2, "onCreate")

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

        val peopleRecyclerView = findViewById<RecyclerView>(R.id.peopleRecyclerView)
        peopleRecyclerView.layoutManager = LinearLayoutManager(this)
        personAdapter = PersonAdapter(personList)
        peopleRecyclerView.adapter = personAdapter

        val assetsRecyclerView = findViewById<RecyclerView>(R.id.assetsRecyclerView)
        assetsRecyclerView.layoutManager = LinearLayoutManager(this)
        assetAdapter = AssetAdapter(assetList) { asset ->
            onAssetSelected(asset) // Callback khi tài sản được chọn
        }
        assetsRecyclerView.adapter = assetAdapter

        //thêm tài sản
        binding.buttonAddAsset.setOnClickListener {
            val assetName = binding.editTextAsset.text.toString()
            val assetValueText = binding.editTextValueAsset.text.toString()

            if (assetName.isNotEmpty() && assetValueText.isNotEmpty()) {
                val assetValue = assetValueText.toLongOrNull()
                if (assetValue != null) {
                    val newAsset = DataAsset(assetName, assetValue)

                    assetAdapter.addAsset(newAsset)

                    binding.editTextAsset.text.clear()
                    binding.editTextValueAsset.text.clear()

                    Log.d(BT2, "Thêm tài sản: $assetName - ${assetValue.formatMoney()}")
                    Toast.makeText(this, "Thêm $assetName thành công", Toast.LENGTH_SHORT).show()
                } else {
                    Log.d(BT2, "Giá trị tài sản không hợp lệ")
                }
            } else {
                Log.d(BT2, "Tên hoặc giá trị tài sản không được để trống")
                Toast.makeText(this, "Thêm tài sản thất bại", Toast.LENGTH_SHORT).show()
            }
        }

        // thêm người
        binding.buttonAddPeople.setOnClickListener {
            val personName = binding.editTextPeople.text.toString()

            if (personName.isNotEmpty() && selectedAssets.isNotEmpty()) {
                // Tạo đối tượng DataPerson với danh sách tài sản của họ
                val newPerson = DataPerson(personName, selectedAssets.map { it.name })

                // Thêm người vào danh sách và cập nhật RecyclerView
                personAdapter.addPerson(newPerson)

                // Xóa nội dung các trường nhập và danh sách tài sản đã chọn
                binding.editTextPeople.text.clear()
                selectedAssets.clear()
                binding.textViewListAssets.text = "Tài sản: "

                Log.d(BT2, "Thêm người: $personName với tài sản: ${newPerson.assets}")
                Toast.makeText(this, "Thêm $personName thành công ", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Tên người và tài sản không được để trống", Toast.LENGTH_SHORT).show()
            }
        }

        binding.personResult.setOnClickListener {
            val intentMain = Intent(this@MainActivity, ResultsScreen::class.java)
            startActivity(intentMain)
            Log.d(BT2, "Chuyển sang màn hình kết quả")
        }

        binding.assetResults.setOnClickListener {
            val intentMain = Intent(this@MainActivity, ResultsScreen::class.java)
            startActivity(intentMain)
            Log.d(BT2, "Chuyển sang màn hình kết quả")
        }
    }

    private fun onAssetSelected(asset: DataAsset) {
        if (!selectedAssets.contains(asset)) {
            selectedAssets.add(asset)
            binding.textViewListAssets.text = "Tài sản: " + selectedAssets.joinToString {it.name}
        }
    }

    companion object {
        private const val BT2 = "baitap2"
        private const val PERSONVIEW = "View Con người"
        private const val ASSETVIEW = "View Tài sản"
    }
}
