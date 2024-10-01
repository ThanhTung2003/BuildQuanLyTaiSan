package com.example.quanlytaisanbt2

import java.io.Serializable


data class Person(
    val id: String,
    val name: String,
    val avatar: String,
    val asset: List<AssetCount>
) : Serializable {
    fun totalAssetValue(assets: List<Asset>): Long {
        return asset.sumOf { assetCount ->
            val singleAsset = assets.find { it.id == assetCount.id }
            singleAsset?.value?.times(assetCount.count) ?: 0
        }
    }
}

data class AssetCount(
    val id: String,
    val count: Int
)

data class PersonResponse(
    val data: List<Person>
)