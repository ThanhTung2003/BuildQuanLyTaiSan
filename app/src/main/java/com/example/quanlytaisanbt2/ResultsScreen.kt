package com.example.quanlytaisanbt2

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

        // Deserialize data from the intent
        val type = object : TypeToken<List<Person>>() {}.type
        val jsonTaxPayers = intent.getStringExtra("taxPayers")
        val jsonNonTaxPayers = intent.getStringExtra("nonTaxPayers")

        val taxPayers: List<Person> = Gson().fromJson(jsonTaxPayers, type)
        val nonTaxPayers: List<Person> = Gson().fromJson(jsonNonTaxPayers, type)

        // Setup RecyclerView for taxpayers
        setupRecyclerView(binding.listPeoplePayTax, taxPayers, true)

        // Setup RecyclerView for non-taxpayers
        setupRecyclerView(binding.listPeopleNoPayTax, nonTaxPayers, true)

        binding.textTotalPeople.text = "Tổng có ${taxPayers.size + nonTaxPayers.size} người trong danh sách"
        binding.textTotalPeoplePayTax.text = "Có ${taxPayers.size} người đóng thuế"
        binding.textTotalPeopleNoPayTax.text = "Có ${nonTaxPayers.size} người không đóng thuế"


        binding.buttonBack.setOnClickListener {
            finish()
            Log.d("baitap2", "Trở về màn hình chính")
        }
    }

    private fun setupRecyclerView(recyclerView: RecyclerView, people: List<Person>, showTotalValue: Boolean) {
        val adapter = PersonAdapter(people.toMutableList(), showTotalValue)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}
