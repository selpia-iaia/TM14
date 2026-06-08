package com.example.imo

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Barang(
    @SerializedName("id") val id: Int,
    @SerializedName("nama_barang") val name: String,
    @SerializedName("kategori") val category: String,
    @SerializedName("stok") val stock: Int,
    @SerializedName("harga") val price: Double,
    @SerializedName("deskripsi") val description: String
) : Serializable
