package com.example.tiendazavaletaapp.ProductosListaAdmin

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup


class ProductosListaAdminAdapter(var list:List<ProductosListaAdmin>): RecyclerView.Adapter<ProductosListaAdminViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductosListaAdminViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ProductosListaAdminViewHolder(inflater,parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ProductosListaAdminViewHolder, position: Int) {
        val productosListaAdmin=list[position]
        holder.bind(productosListaAdmin)
    }
    fun setDatos(datos: List<ProductosListaAdmin>) {
        this.list = datos
        notifyDataSetChanged()
    }
}