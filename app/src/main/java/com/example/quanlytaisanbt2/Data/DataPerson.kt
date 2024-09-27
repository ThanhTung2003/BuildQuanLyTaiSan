package com.example.quanlytaisanbt2.Data

import java.io.Serializable

data class DataPerson(
    val name: String,
    val assets: List<String>
): Serializable