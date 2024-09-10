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

    private fun addAsset(newAsset: Asset) {
        // tìm tài sản đã tồn tại
        val existingAsset = assets.find { it.getName() == newAsset.getName() }
        if (existingAsset != null) {
            // cộng dồn số lượng cho tài sản đã tồn tại
            existingAsset.quantity += newAsset.quantity
        } else {
            // thêm tài sản mới vào danh sách
            assets.add(newAsset)
        }
        // cập nhật tổng giá trị tài sản
        totalValue = assets.sumOf { it.value * it.quantity }
    }

    private fun getAssetsInfo(): String {
        return assets.joinToString("\n") { asset ->
            "   * ${asset.quantity} ${asset.getName()}: ${(asset.value * asset.quantity).formatMoney()}"
        }
    }

    fun getTotalValue():Long{
        return totalValue
    }

}