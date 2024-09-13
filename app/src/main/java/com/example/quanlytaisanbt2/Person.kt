package com.example.quanlytaisanbt2

import android.util.Log
import com.example.quanlytaisanbt2.UI.formatMoney

data class Person(
    private val name: String,
    private var totalValue: Long = 0,
    private val category: String = "Con người"
):Object {

    private val assets = mutableListOf<Asset>()

    override fun getName():String {
        return name
    }

    fun getInfoTax(isFee: Boolean = true): String {
        var result = "  + ${getName()}: ${getTotalValue().formatMoney()}"
        if (!isFee) {
            result += "\n${getAssetsInfo()}"
        }
        return result
    }

    override fun getInfo(): String {
        return "$name: $totalValue vnđ"
    }

    override fun getcategory(): String {
        return category
    }

    fun addAssetList(asset: Asset, quantity: Int) {
        for (i in 1..quantity) {
            addAsset(asset)
        }
    }

    fun addAsset(newAsset: Asset) {
        // Tìm tài sản đã tồn tại
        val existingAsset = assets.find { it.getName() == newAsset.getName() }
        if (existingAsset != null) {
            // Cộng dồn số lượng cho tài sản đã tồn tại
            existingAsset.quantity += newAsset.quantity
        } else {
            // Thêm tài sản mới vào danh sách
            assets.add(newAsset)
        }
        // Cập nhật tổng giá trị tài sản
        totalValue = assets.sumOf { it.value * it.quantity }

    }


    fun getAssetsInfo(): String {
        return assets.joinToString("\n") { asset ->
            "   * ${asset.quantity} ${asset.getName()}: ${(asset.value * asset.quantity).formatMoney()}"
        }
    }



    fun getTotalValue():Long{
        return totalValue
    }

}