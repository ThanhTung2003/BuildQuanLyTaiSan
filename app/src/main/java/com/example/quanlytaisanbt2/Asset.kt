package com.example.quanlytaisanbt2


data class Asset(
    val id: String,
    val name: String,
    val value: Long,
    val icon: String
)

data class AssetResponse(
    val data: List<Asset>
)
