package com.example.quanlytaisanbt2.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.quanlytaisanbt2.R

class MovieAdapter(private val dataMovieList: List<DataMovie>) : RecyclerView.Adapter<MovieAdapter.ViewHolderMovieClass>() {

    class ViewHolderMovieClass(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieImage: ImageView = itemView.findViewById(R.id.imageMovie)
        val titleMovie: TextView = itemView.findViewById(R.id.title_Movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMovieClass {
        val itemMovie = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolderMovieClass(itemMovie)
    }

    override fun onBindViewHolder(holder: ViewHolderMovieClass, position: Int) {
        val currentItemMovie = dataMovieList[position]

        Glide.with(holder.itemView.context)
            .load(currentItemMovie.dataImageMovieUrl) // URL của ảnh
            .placeholder(R.drawable.cahong) // hiển thị ảnh tạm thời khi ảnh chính đang tải
            .error(R.drawable.baseline_wifi_off_24) // hiển thị ảnh khi load ảnh thất bại
            .into(holder.movieImage) // ImageView cần load ảnh vào


        holder.titleMovie.text = currentItemMovie.dataTitleMovie
    }

    override fun getItemCount(): Int {
        return dataMovieList.size
    }
}
