package com.example.quanlytaisanbt2.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.quanlytaisanbt2.databinding.ItemMovieBinding
import com.example.quanlytaisanbt2.movie.DataMovie

class SearchAdapter(private val searchList: List<DataMovie>) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    class SearchViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: DataMovie) {
            binding.titleMovie.text = movie.dataTitleMovie
            Glide.with(binding.imageMovie.context).load(movie.dataImageMovieUrl).into(binding.imageMovie)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(searchList[position])
    }

    override fun getItemCount() = searchList.size
}
