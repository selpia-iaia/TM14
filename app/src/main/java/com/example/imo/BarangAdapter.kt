package com.example.imo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.NumberFormat
import java.util.Locale

class BarangAdapter(
    private var listBarang: List<Barang>,
    private val onItemClick: (Barang) -> Unit
) : RecyclerView.Adapter<BarangAdapter.BarangViewHolder>() {

    class BarangViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNamaBarang: TextView = view.findViewById(R.id.tvTitle)
        val tvDeskripsiBarang: TextView = view.findViewById(R.id.tvDesc)
        val tvHargaBarang: TextView = view.findViewById(R.id.tvPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarangViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_barang, parent, false)
        return BarangViewHolder(view)
    }

    override fun onBindViewHolder(holder: BarangViewHolder, position: Int) {
        val barang = listBarang[position]
        holder.tvNamaBarang.text = barang.name
        holder.tvDeskripsiBarang.text = barang.description
        
        val format = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
        var formattedPrice = format.format(barang.price)
        // Ensure format is Rp100.000,00 (no space) as in screenshot
        formattedPrice = formattedPrice.replace("Rp ", "Rp").replace("Rp", "Rp") 
        holder.tvHargaBarang.text = formattedPrice
            
        holder.itemView.setOnClickListener { onItemClick(barang) }
    }

    override fun getItemCount(): Int = listBarang.size

    fun updateData(newList: List<Barang>) {
        listBarang = newList
        notifyDataSetChanged()
    }
}
