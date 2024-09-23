package com.example.quanlytaisanbt2.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quanlytaisanbt2.databinding.ItemCategoryBinding
import com.example.quanlytaisanbt2.movie.MovieAdapter

class CategoryAdapter(private val categoryList: List<DataCategory>) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(private val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(category: DataCategory) {
            binding.titleCategory.text = category.nameCategory

            // Cài đặt adapter cho RecyclerView ngang
            val movieAdapter = MovieAdapter(category.listMovies)
            binding.recyclerViewMovie.layoutManager = LinearLayoutManager(binding.recyclerViewMovie.context, LinearLayoutManager.HORIZONTAL, false)
            binding.recyclerViewMovie.adapter = movieAdapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categoryList[position])
    }

    override fun getItemCount() = categoryList.size
}
