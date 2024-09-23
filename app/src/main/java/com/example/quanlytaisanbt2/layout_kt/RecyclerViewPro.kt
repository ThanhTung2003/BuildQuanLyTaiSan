package com.example.quanlytaisanbt2.layout_kt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quanlytaisanbt2.R
import com.example.quanlytaisanbt2.category.CategoryAdapter
import com.example.quanlytaisanbt2.category.DataCategory
import com.example.quanlytaisanbt2.databinding.ActivityRecyclerViewProBinding
import com.example.quanlytaisanbt2.movie.DataMovie

class RecyclerViewPro : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerViewProBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewProBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val movieList = listOf(
            DataMovie("https://homepage.momocdn.net/blogscontents/momo-upload-api-200219103835-637177055151245839.jpg", "Zootopia"),
            DataMovie("https://homepage.momocdn.net/blogscontents/momo-upload-api-200219103915-637177055554551480.jpg", "Kung Fu Panda 3"),
            DataMovie("https://homepage.momocdn.net/blogscontents/momo-upload-api-200219104023-637177056237162869.jfif", "Toy Story 3"),
            DataMovie("https://homepage.momocdn.net/blogscontents/momo-upload-api-200219113516-637177089165069637.jpg", "Incredibles 2"),
            DataMovie("https://homepage.momocdn.net/blogscontents/momo-upload-api-200219113751-637177090711186072.jfif", "Big Hero 6")
        )


        val categoryList = listOf(
            DataCategory("Action", movieList),
            DataCategory("Comedy", movieList),
            DataCategory("Drama", movieList),
            DataCategory("Sci-Fi", movieList),
            DataCategory("Horror", movieList)
        )

        // Cài đặt adapter cho RecyclerView dọc
        val categoryAdapter = CategoryAdapter(categoryList)
        binding.RecyclerViewPro.layoutManager = LinearLayoutManager(this)
        binding.RecyclerViewPro.adapter = categoryAdapter
    }
}
