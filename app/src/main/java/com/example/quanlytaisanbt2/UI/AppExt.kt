package com.example.quanlytaisanbt2.UI

import java.text.NumberFormat
import java.util.Locale

fun Long.formatMoney(): String {
    val numberFormat = NumberFormat.getInstance(Locale("vi","VN"))
    return "${numberFormat.format(this)} vnđ"
}
