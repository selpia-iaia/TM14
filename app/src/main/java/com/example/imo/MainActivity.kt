package com.example.imo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvBarang: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var barangAdapter: BarangAdapter
    private val viewModel: BarangViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "I-mo App"

        rvBarang = findViewById(R.id.rv_barang) 
        progressBar = findViewById(R.id.progress_bar)

        setupRecyclerView()
        observeViewModel()

        viewModel.fetchBarang()
    }

    private fun setupRecyclerView() {
        barangAdapter = BarangAdapter(emptyList()) { barang ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("extra_barang", barang)
            startActivity(intent)
        }
        rvBarang.layoutManager = LinearLayoutManager(this)
        rvBarang.adapter = barangAdapter
    }

    private fun observeViewModel() {
        viewModel.listBarang.observe(this) { listBarang ->
            if (listBarang.isEmpty()) {
                showDummyData()
            } else {
                barangAdapter.updateData(listBarang)
            }
        }

        viewModel.isLoading.observe(this) { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.error.observe(this) { errorMessage ->
            if (errorMessage != null) {
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                showDummyData()
            }
        }
    }

    private fun showDummyData() {
        val dummyList = listOf(
            Barang(1, "Laptop Asus", "Elektronik", 10, 8500000.0, "Laptop kantor"),
            Barang(2, "Printer Epson", "Elektronik", 5, 2500000.0, "Printer warna"),
            Barang(3, "Mouse Logitech", "Aksesoris", 20, 150000.0, "Mouse wireless"),
            Barang(4, "Keyboard Mechanical", "Aksesoris", 15, 450000.0, "Keyboard gaming")
        )
        barangAdapter.updateData(dummyList)
    }
}
