package com.example.quanlytaisanbt2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quanlytaisanbt2.Data.DataPerson
import com.example.quanlytaisanbt2.R
import com.example.quanlytaisanbt2.UI.formatMoney

class PersonAdapter(private val personList: MutableList<DataPerson>) : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val personName: TextView = itemView.findViewById(R.id.personName)
        val personAssets: TextView = itemView.findViewById(R.id.personAssets)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.person_item, parent, false)
        return PersonViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = personList[position]
        holder.personName.text = person.name

        holder.personAssets.text =": " + person.assets.joinToString(", ")
    }
    override fun getItemCount(): Int {
        return personList.size
    }

    fun addPerson(person: DataPerson) {
        personList.add(person)
        notifyItemInserted(personList.size - 1)
    }

    fun getPersonList(): MutableList<DataPerson> {
        return personList
    }
}

