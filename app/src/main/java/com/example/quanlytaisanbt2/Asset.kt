package com.example.quanlytaisanbt2

import com.example.quanlytaisanbt2.UI.formatMoney

data class Asset(
    @get:JvmName("getAssetName")
    val name: String,
    var value: Long,
    private val category: String = "Tài sản",
    var quantity: Int = 1
) : Objectt {
    override fun getName(): String = name

    override fun getInfo(): String {
        return "$name: ${(value).formatMoney()} "
    }

    override fun getCategory(): String {
        return category
    }

}