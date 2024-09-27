package com.example.quanlytaisanbt2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.quanlytaisanbt2.databinding.ActivityMainBinding
import com.example.quanlytaisanbt2.databinding.ActivityResultsScreenBinding

class ResultsScreen : AppCompatActivity() {
    private lateinit var binding: ActivityResultsScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultsScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Nhận dữ liệu từ Intent
        val totalPeople = intent.getIntExtra("totalPeople", 0)
        val taxPayersCount = intent.getIntExtra("taxPayersCount", 0)
        val nonTaxPayersCount = intent.getIntExtra("nonTaxPayersCount", 0)

        // Hiển thị dữ liệu lên TextView
        binding.textTotalPeople.text = "Tổng có $totalPeople người trong danh sách"
        binding.textTotalPeoplePayTax.text = "Có $taxPayersCount người đóng thuế"
        binding.textTotalPeopleNoPayTax.text = "Có $nonTaxPayersCount người không đóng thuế"

        binding.buttonBack.setOnClickListener {
            finish()
            Log.d("baitap2", "Trở về màn hình chính")
        }
    }
}