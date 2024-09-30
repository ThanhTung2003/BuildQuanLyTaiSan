package com.example.quanlytaisanbt2.Controller

import com.example.quanlytaisanbt2.Asset
import com.example.quanlytaisanbt2.Person
import com.example.quanlytaisanbt2.adapter.PersonAdapter

class PersonController(private val personAdapter: PersonAdapter) {
    fun addPerson(personName: String, selectedAssets: List<Asset>): Boolean {
        if (personName.isNotEmpty() && selectedAssets.isNotEmpty()) {
            val newPerson = Person(personName)
            selectedAssets.forEach { asset ->
                newPerson.addAsset(asset, 1)
            }
            personAdapter.addPerson(newPerson)
            return true
        }
        return false
    }
}


