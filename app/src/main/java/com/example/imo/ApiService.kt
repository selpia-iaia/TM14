package com.example.imo

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("db_imo/api_barang.php")
    fun getBarang(): Call<List<Barang>>
}
