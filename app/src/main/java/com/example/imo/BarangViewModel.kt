package com.example.imo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BarangViewModel : ViewModel() {
    private val _listBarang = MutableLiveData<List<Barang>>()
    val listBarang: LiveData<List<Barang>> get() = _listBarang

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun fetchBarang() {
        _isLoading.value = true
        RetrofitClient.apiService.getBarang().enqueue(object : Callback<List<Barang>> {
            override fun onResponse(call: Call<List<Barang>>, response: Response<List<Barang>>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listBarang.value = response.body() ?: emptyList()
                } else {
                    _error.value = "Gagal memuat data: ${response.code()}"
                }
            }

            override fun onFailure(call: Call<List<Barang>>, t: Throwable) {
                _isLoading.value = false
                _error.value = "Error: ${t.message}"
            }
        })
    }
}
