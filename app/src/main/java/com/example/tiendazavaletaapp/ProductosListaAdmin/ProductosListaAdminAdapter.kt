package com.example.tiendazavaletaapp.ProductosListaAdmin

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup


class ProductosListaAdminAdapter(var list:List<ProductosListaAdmin>): RecyclerView.Adapter<ProductosListaAdminViewHolder>() {

    private var productosFiltrados: List<ProductosListaAdmin> = list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductosListaAdminViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ProductosListaAdminViewHolder(inflater,parent)
    }

    override fun getItemCount(): Int {
        return productosFiltrados.size
    }

    override fun onBindViewHolder(holder: ProductosListaAdminViewHolder, position: Int) {
        holder.bind(productosFiltrados[position])
    }
    fun setDatos(datos: List<ProductosListaAdmin>) {
        this.list = datos
        this.productosFiltrados = datos
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        productosFiltrados = if (query.isEmpty()) {
            list
        } else {
            list.filter {
                it.nProducto.contains(query, ignoreCase = true) ||
                        it.marca.contains(query, ignoreCase = true)
            }
        }
        notifyDataSetChanged()
    }
}