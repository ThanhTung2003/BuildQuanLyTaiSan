package com.example.quanlytaisanbt2.layout

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.quanlytaisanbt2.databinding.ActivityResultsScreenBinding

class ResultsScreen : AppCompatActivity() {
    private lateinit var binding: ActivityResultsScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultsScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonBack.setOnClickListener {
            finish()
            Log.d("baitap2", "Trở về màn hình chính")
        }
    }
}