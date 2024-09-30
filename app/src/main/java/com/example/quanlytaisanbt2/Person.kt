package com.example.quanlytaisanbt2


data class Person(
    val id: String,
    val name: String,
    val avatar: String,
    val asset: List<AssetCount>
)

data class AssetCount(
    val id: String,
    val count: Int
)

data class PersonResponse(
    val data: List<Person>
)