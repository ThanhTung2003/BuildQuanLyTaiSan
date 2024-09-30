package com.example.quanlytaisanbt2

import com.example.quanlytaisanbt2.UI.formatMoney

data class Person(
    val personName: String,
    private var totalValue: Long = 0,
    private val category: String = "Con người"
):Objectt {

    val assets = mutableListOf<Asset>()



    fun getInfoTax(isFee: Boolean = true): String {
        var result = "  + ${personName}: ${getTotalValue().formatMoney()}"
        if (!isFee) {
            result += "\n${getAssetsInfo()}"
        }
        return result
    }



    override fun getCategory(): String {
        return category
    }

    override fun getName(): String = personName

    override fun getInfo(): String {
        return "$personName: $totalValue "
    }

    fun addAssetList(asset: Asset, quantity: Int) {
        for (i in 1..quantity) {
            addAsset(asset)
        }
    }

    fun addAsset(newAsset: Asset, quantity: Int = 1) {
        // Tìm tài sản đã tồn tại trong danh sách
        val existingAsset = assets.find { it.name == newAsset.name }
        if (existingAsset != null) {
            // Cộng dồn số lượng nếu tài sản đã tồn tại
            existingAsset.quantity += quantity
        } else {
            // Sao chép tài sản và thiết lập số lượng, sau đó thêm vào danh sách
            val assetToAdd = newAsset.copy(quantity = quantity)
            assets.add(assetToAdd)
        }
    }

    fun getAssetsInfo(): String {
        return assets.joinToString("\n") { asset ->
            "   * ${asset.quantity} ${asset.getName()}: ${(asset.value * asset.quantity).formatMoney()}"
        }
    }

    fun getTotalValue(): Long {
        return totalValue
    }

    fun getFullInfo(): String {
        val assetsInfo = assets.joinToString(", ") { it.getName() }
        return "$personName: $assetsInfo"
    }


}