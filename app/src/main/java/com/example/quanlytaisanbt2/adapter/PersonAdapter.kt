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

class PersonAdapter(private val persons: List<Person>, private val assets: List<Asset>,private val showTotalValue: Boolean) : RecyclerView.Adapter<PersonAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageAvatar: ImageView = view.findViewById(R.id.imageAvatar)
        val textPersonName: TextView = view.findViewById(R.id.personName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.person_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val person = persons[position]
        Glide.with(holder.itemView.context).load(person.avatar).into(holder.imageAvatar)

        if(showTotalValue) {
            val totalValue = person.totalAssetValue(assets).formatMoney()
            holder.textPersonName.text = "${person.name} - $totalValue"
        } else {
            val assetMap = assets.associateBy({ it.id }, { it.name })
            val assetsDisplay = person.asset.flatMap { assetCount ->
                val assetName = assetMap[assetCount.id] ?: "Unknown Asset"
                List(assetCount.count) { assetName }
            }.joinToString(", ")
            holder.textPersonName.text = "${person.name}: $assetsDisplay"
        }
    }


    override fun getItemCount(): Int = persons.size
}


