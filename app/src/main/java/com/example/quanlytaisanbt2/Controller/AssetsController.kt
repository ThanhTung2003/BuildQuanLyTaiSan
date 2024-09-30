package com.example.quanlytaisanbt2.Controller

import com.example.quanlytaisanbt2.Asset
import com.example.quanlytaisanbt2.adapter.AssetAdapter

class AssetsController(private val assetAdapter: AssetAdapter) {
    fun addAsset(assetName: String, assetValueText: String): Boolean {
        val assetValue = assetValueText.toLongOrNull()
        return if (assetName.isNotEmpty() && assetValue != null) {
            val newAsset = Asset(assetName, assetValue)
            assetAdapter.addAsset(newAsset)  // Thêm vào assetAdapter
            assetAdapter.notifyItemInserted(assetAdapter.itemCount - 1)  // Thông báo thêm mới
            true
        } else {
            false
        }
    }
}

