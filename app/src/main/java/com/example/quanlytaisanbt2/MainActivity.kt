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

        // Khởi tạo RecyclerView cho con người
        val peopleRecyclerView = findViewById<RecyclerView>(R.id.peopleRecyclerView)
        peopleRecyclerView.layoutManager = LinearLayoutManager(this)
        personAdapter = PersonAdapter(personList)
        peopleRecyclerView.adapter = personAdapter

        // Khởi tạo RecyclerView cho tài sản với một danh sách rỗng
        val assetsRecyclerView = findViewById<RecyclerView>(R.id.assetsRecyclerView)
        assetsRecyclerView.layoutManager = LinearLayoutManager(this)
        assetAdapter = AssetAdapter(mutableListOf()) // Khởi tạo adapter với danh sách rỗng
        assetsRecyclerView.adapter = assetAdapter

        // thêm tài sản
        binding.buttonAddAsset.setOnClickListener {
            val assetName = binding.editTextAsset.text.toString()
            val assetValueText = binding.editTextValueAsset.text.toString()

            if (assetName.isNotEmpty() && assetValueText.isNotEmpty()) {
                val assetValue = assetValueText.toLongOrNull()
                if (assetValue != null) {
                    // Thêm tài sản vào adapter
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

        binding.buttonAddPeople.setOnClickListener {
            val personName = binding.editTextPeople.text.toString()

            if (personName.isNotEmpty()) {
                // Thêm người mới vào danh sách
                val newPerson = DataPerson(personName, emptyList()) // Tạm thời không thêm tài sản
                personAdapter.addPerson(newPerson) // Thêm người qua personAdapter

                // Reset trường nhập liệu
                binding.editTextPeople.text.clear()

                Log.d("BT2", "Thêm người: $personName")
                Toast.makeText(this, "Thêm $personName thành công", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Tên người không được để trống", Toast.LENGTH_SHORT).show()
            }
        }

        // Điều hướng sang màn hình kết quả
        binding.personResult.setOnClickListener {
            val intentMain = Intent(this@MainActivity, ResultsScreen::class.java)
            startActivity(intentMain)
            Log.d(BT2, "Chuyển sang màn hình kết quả")
        }

        binding.assetResults.setOnClickListener {
            val intentMain = Intent (this@MainActivity, ResultsScreen::class.java)
            startActivity(intentMain)
            Log.d(BT2, "Chuyển sang màn hình kết quả")
        }
    }

    companion object {
        private const val BT2 = "baitap2"
        private const val PERSONVIEW = "View Con người"
        private const val ASSETVIEW = "View Tài sản"
    }
}


