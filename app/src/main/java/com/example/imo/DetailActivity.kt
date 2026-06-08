package com.example.imo

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat
import java.util.Locale

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Detail Barang"

        val barang = intent.getSerializableExtra("extra_barang") as? Barang

        if (barang != null) {
            val tvNama: TextView = findViewById(R.id.tv_detail_nama)
            val tvKategori: TextView = findViewById(R.id.tv_detail_kategori)
            val tvHarga: TextView = findViewById(R.id.tv_detail_harga)
            val tvStok: TextView = findViewById(R.id.tv_detail_stok)
            val tvDeskripsi: TextView = findViewById(R.id.tv_detail_deskripsi)

            tvNama.text = barang.name
            tvKategori.text = "Kategori: ${barang.category}"
            tvStok.text = "Sisa Stok: ${barang.stock}"
            tvDeskripsi.text = barang.description

            val format = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
            tvHarga.text = format.format(barang.price)
            
            supportActionBar?.title = barang.name
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
