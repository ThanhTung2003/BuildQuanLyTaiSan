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

    companion object {
        const val ARG_DATA_LIST_PERSON = "ARG_DATA_LIST_PERSON"
    }
    private lateinit var binding: ActivityResultsScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultsScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val type = object : TypeToken<List<Person>>() {}.type
        val taxPayers: List<Person> = Gson().fromJson(intent.getStringExtra("taxPayers"), type)
        val nonTaxPayers: List<Person> = Gson().fromJson(intent.getStringExtra("nonTaxPayers"), type)

        setupRecyclerView(binding.listPeoplePayTax, taxPayers)
        setupRecyclerView(binding.listPeopleNoPayTax, nonTaxPayers)

        binding.textTotalPeople.text = "Tổng có ${taxPayers.size + nonTaxPayers.size} người trong danh sách"
        binding.textTotalPeoplePayTax.text = "Có ${taxPayers.size} người đóng thuế"
        binding.textTotalPeopleNoPayTax.text = "Có ${nonTaxPayers.size} người không đóng thuế"

        setupRecyclerView(binding.listPeoplePayTax, taxPayers)
        setupRecyclerView(binding.listPeopleNoPayTax, nonTaxPayers)

    }

    private fun setupRecyclerView(recyclerView: RecyclerView, people: List<Person>) {
        val adapter = PersonAdapter(people.toMutableList())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

}