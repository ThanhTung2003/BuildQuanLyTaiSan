package com.example.quanlytaisanbt2.Controller

import com.example.quanlytaisanbt2.Data.DataAsset
import com.example.quanlytaisanbt2.Data.DataPerson
import com.example.quanlytaisanbt2.adapter.PersonAdapter

class PersonController(private val personAdapter: PersonAdapter) {
    private val personList = personAdapter.getPersonList()
    fun addPerson(personName: String, selectedAssets: List<DataAsset>): Boolean {
        return if (personName.isNotEmpty() && selectedAssets.isNotEmpty()) {
            val newPerson = DataPerson(personName, selectedAssets.map { it.name })

            personAdapter.addPerson(newPerson)
            personAdapter.notifyItemInserted(personAdapter.itemCount - 1)

            true
        } else {
            false
        }
    }
//    fun addPerson(personName: String, selectedAssets: List<DataAsset>): Boolean {
//        return if (personName.isNotEmpty() && selectedAssets.isNotEmpty()) {
//            // Tạo đối tượng DataPerson mới với danh sách selectedAssets hoàn chỉnh
//            val newPerson = DataPerson(personName, selectedAssets)
//
//            // Thêm người mới vào PersonAdapter
//            personAdapter.addPerson(newPerson)
//            personAdapter.notifyItemInserted(personAdapter.itemCount - 1)
//
//            true
//        } else {
//            false
//        }
//    }

}

