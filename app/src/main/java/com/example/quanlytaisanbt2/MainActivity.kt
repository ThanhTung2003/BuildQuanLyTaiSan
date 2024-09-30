package com.example.quanlytaisanbt2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quanlytaisanbt2.Controller.AssetsController
import com.example.quanlytaisanbt2.Controller.PersonController
import com.example.quanlytaisanbt2.adapter.AssetAdapter
import com.example.quanlytaisanbt2.adapter.PersonAdapter
import com.example.quanlytaisanbt2.databinding.ActivityMainBinding
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var personAdapter: PersonAdapter
    private lateinit var assetAdapter: AssetAdapter
    private val personList = mutableListOf<Person>()
    private val selectedAssets = mutableListOf<Asset>()
    private val assetList = mutableListOf<Asset>()
    private lateinit var assetsController: AssetsController

    @SuppressLint("MissingInflatedId", "SetTextI18n", "NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(1888)
        installSplashScreen()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(BT2, "onCreate")

        personAdapter = PersonAdapter(personList)
        val personController = PersonController(personAdapter)

        assetAdapter = AssetAdapter(assetList) { asset -> onAssetSelected(asset) }

        assetsController = AssetsController(assetAdapter)


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
            onAssetSelected(asset)
        }
        assetsRecyclerView.adapter = assetAdapter

        // Thêm tài sản
        binding.buttonAddAsset.setOnClickListener {
            val assetName = binding.editTextAsset.text.toString().trim()
            val assetValueText = binding.editTextValueAsset.text.toString().trim()

            if (assetName.isNotEmpty() && assetValueText.isNotEmpty()) {
                if (assetsController.addAsset(assetName, assetValueText)) {
                    assetAdapter.notifyDataSetChanged()
                    binding.editTextAsset.text.clear()
                    binding.editTextValueAsset.text.clear()
                    Toast.makeText(this, "Thêm $assetName thành công", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Thêm tài sản thất bại", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin tài sản", Toast.LENGTH_SHORT).show()
            }
        }

        // Thêm người
        binding.buttonAddPeople.setOnClickListener {
            val personName = binding.editTextPeople.text.toString().trim()

            if (personName.isNotEmpty()) {
                if (selectedAssets.isNotEmpty()) {
                    if (personController.addPerson(personName, selectedAssets)) {
                        personAdapter.notifyDataSetChanged()
                        binding.editTextPeople.text.clear()
                        selectedAssets.clear()
                        binding.textViewListAssets.text = "Tài sản: "
                        Toast.makeText(this, "Thêm người thành công", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Cảnh báo")
                    builder.setMessage("Bắt buộc phải thêm tài sản cho con người")
                    builder.setPositiveButton("Đóng") { dialog, _ ->
                        dialog.dismiss()
                    }
                    val alertDialog = builder.create()
                    alertDialog.show()
                }
            } else {
                Toast.makeText(this, "Tên người không được để trống", Toast.LENGTH_SHORT).show()
            }
        }

        //xem ket qua
        binding.personResult.setOnClickListener {
            val taxPayers = personList.filter { person ->
                person.assets.sumOf { asset -> asset.value * asset.quantity } >= 1000000000
            }
            val nonTaxPayers = personList.filter { person ->
                person.assets.sumOf { asset -> asset.value * asset.quantity } < 1000000000
            }

            val intentMain = Intent(this@MainActivity, ResultsScreen::class.java)
            intentMain.putExtra("totalPeople", personList.size)
            intentMain.putExtra("taxPayers", Gson().toJson(taxPayers))
            intentMain.putExtra("nonTaxPayers", Gson().toJson(nonTaxPayers))
            startActivity(intentMain)
        }

        binding.assetResults.setOnClickListener {
            val taxPayers = personList.filter { person ->
                person.assets.sumOf { asset -> asset.value * asset.quantity } >= 1000000000
            }
            val nonTaxPayers = personList.filter { person ->
                person.assets.sumOf { asset -> asset.value * asset.quantity } < 1000000000
            }

            val intentMain = Intent(this@MainActivity, ResultsScreen::class.java)
            intentMain.putExtra("totalPeople", personList.size)
            intentMain.putExtra("taxPayers", Gson().toJson(taxPayers))
            intentMain.putExtra("nonTaxPayers", Gson().toJson(nonTaxPayers))
            startActivity(intentMain)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun onAssetSelected(asset: Asset) {
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
