package com.example.quanlytaisanbt2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quanlytaisanbt2.adapter.PersonAdapter
import com.example.quanlytaisanbt2.databinding.ActivityResultsScreenBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ResultsScreen : AppCompatActivity() {
    private lateinit var binding: ActivityResultsScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultsScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val totalPeople = intent.getIntExtra("totalPeople", 0)
        val taxPayersJson = intent.getStringExtra("taxPayers")
        val nonTaxPayersJson = intent.getStringExtra("nonTaxPayers")

        // Deserialize data
        val type = object : TypeToken<List<Person>>() {}.type
        val taxPayers: List<Person> = Gson().fromJson(taxPayersJson, type)
        val nonTaxPayers: List<Person> = Gson().fromJson(nonTaxPayersJson, type)
        val assetsJson = intent.getStringExtra("assets")
        val assetsType = object : TypeToken<List<Asset>>() {}.type
        val assets: List<Asset> = Gson().fromJson(assetsJson, assetsType)

        // Setup RecyclerViews
        setupRecyclerView(binding.listPeoplePayTax, taxPayers,assets)
        setupRecyclerView(binding.listPeopleNoPayTax, nonTaxPayers,assets)

        // Update text views
        binding.textTotalPeople.text = "Tổng có $totalPeople người trong danh sách"
        binding.textTotalPeoplePayTax.text = "Có ${taxPayers.size} người đóng thuế"
        binding.textTotalPeopleNoPayTax.text = "Có ${nonTaxPayers.size} người không đóng thuế"

        binding.buttonBack.setOnClickListener {
            finish()
            Log.d("baitap2", "Trở về màn hình chính")
        }
    }

    private fun setupRecyclerView(recyclerView: RecyclerView, people: List<Person>, assets: List<Asset>) {
        val adapter = PersonAdapter(people, assets,true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}

