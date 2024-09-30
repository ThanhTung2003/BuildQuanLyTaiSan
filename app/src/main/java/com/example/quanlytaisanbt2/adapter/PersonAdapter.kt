package com.example.quanlytaisanbt2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.quanlytaisanbt2.Asset
import com.example.quanlytaisanbt2.Person
import com.example.quanlytaisanbt2.R
import com.example.quanlytaisanbt2.UI.formatMoney

class PersonAdapter(private val persons: List<Person>, private val assets: List<Asset>) : RecyclerView.Adapter<PersonAdapter.ViewHolder>() {


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageAvatar: ImageView = view.findViewById(R.id.imageAvatar)
        val textPersonName: TextView = view.findViewById(R.id.personName)
        val personAssets: TextView = itemView.findViewById(R.id.personAssets)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.person_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val person = persons[position]
        holder.textPersonName.text = person.name
        Glide.with(holder.itemView.context).load(person.avatar).into(holder.imageAvatar)

        // Tạo danh sách tài sản với số lượng hiển thị lặp lại
        val assetsDisplay = person.asset.flatMap { assetCount ->
            // Tìm tên tài sản dựa trên id của tài sản
            val assetName = assets.find { it.id == assetCount.id }?.name
            // Tạo danh sách tên tài sản lặp lại theo số lượng
            List(assetCount.count) { assetName ?: "Unknown Asset" }
        }.joinToString(", ")

        holder.personAssets.text = ": $assetsDisplay"
    }



    override fun getItemCount(): Int = persons.size
}


