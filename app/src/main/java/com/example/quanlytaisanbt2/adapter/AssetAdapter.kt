package com.example.quanlytaisanbt2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quanlytaisanbt2.Asset
import com.example.quanlytaisanbt2.R
import com.example.quanlytaisanbt2.UI.formatMoney

class AssetAdapter(private val assetList: MutableList<Asset>, private val onItemClick: (Asset) -> Unit) : RecyclerView.Adapter<AssetAdapter.AssetViewHolder>() {

    class AssetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val assetName: TextView = itemView.findViewById(R.id.assetName)
        val assetValue: TextView = itemView.findViewById(R.id.assetValue)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.asset_item, parent, false)
        return AssetViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AssetViewHolder, position: Int) {
        val asset = assetList[position]
        holder.assetName.text = asset.name
        holder.assetValue.text ="- "+ asset.value.formatMoney()

        holder.itemView.setOnClickListener {
            onItemClick(asset)
        }
    }

    override fun getItemCount(): Int {
        return assetList.size
    }

    fun addAsset(asset: Asset) {
        assetList.add(asset)
        notifyItemInserted(assetList.size - 1)
    }
}

