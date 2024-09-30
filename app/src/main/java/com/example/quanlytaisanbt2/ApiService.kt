package com.example.quanlytaisanbt2

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("xprogamer/assets")
    fun getAssets(): Call<AssetResponse>  // Lưu ý Call<AssetResponse>

    @GET("xprogamer/persons")
    fun getPersons(): Call<PersonResponse>  // Lưu ý Call<PersonResponse>
}



