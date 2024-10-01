package com.example.quanlytaisanbt2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.quanlytaisanbt2.Asset
import com.example.quanlytaisanbt2.R
import com.example.quanlytaisanbt2.UI.formatMoney

class AssetAdapter(private val assets: List<Asset>) : RecyclerView.Adapter<AssetAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageAsset: ImageView = view.findViewById(R.id.imageAsset)
        val textAssetName: TextView = view.findViewById(R.id.assetName)
        val assetValue: TextView = itemView.findViewById(R.id.assetValue)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.asset_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val asset = assets[position]
        holder.textAssetName.text = asset.name
        Glide.with(holder.itemView.context)
            .load(asset.icon)
            .placeholder(R.drawable.icon_loading)
            .error(R.drawable.icon_error)
            .into(holder.imageAsset)

        holder.assetValue.text = "- " + asset.value.formatMoney()
    }

    override fun getItemCount(): Int = assets.size
}


