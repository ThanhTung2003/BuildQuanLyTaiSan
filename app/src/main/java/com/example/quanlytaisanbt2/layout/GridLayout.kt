package com.example.quanlytaisanbt2.layout

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quanlytaisanbt2.R
import com.example.quanlytaisanbt2.databinding.ActivityGridLayoutBinding
import com.example.quanlytaisanbt2.databinding.ActivityResultsScreenBinding

class GridLayout : AppCompatActivity() {
    private lateinit var binding: ActivityGridLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGridLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}