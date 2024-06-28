package com.example.tiendazavaletaapp.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ProductosHomeAdapter(val list:List<ProductosHome>): RecyclerView.Adapter<ProductosHomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductosHomeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ProductosHomeViewHolder(inflater,parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ProductosHomeViewHolder, position: Int) {
        val masVendidos=list[position]
        holder.bind(masVendidos)
    }
}