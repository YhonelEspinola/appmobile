package com.example.tiendazavaletaapp.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.ProductosListaAdmin.ProductosListaAdmin

class BuscarAdapter() : RecyclerView.Adapter<ListCategoriaViewHolder>() {

    private var productos= emptyList<ProductosListaAdmin>()
    private var productosFiltrados: List<ProductosListaAdmin> = productos

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCategoriaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ListCategoriaViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ListCategoriaViewHolder, position: Int) {
        holder.bind(productosFiltrados[position])
    }

    override fun getItemCount(): Int {
        return productosFiltrados.size
    }

    fun setDatos(productos: List<ProductosListaAdmin>) {
        this.productos = productos
        this.productosFiltrados = productos
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        productosFiltrados = if (query.isEmpty()) {
            productos
        } else {
            productos.filter {
                it.nProducto.contains(query, ignoreCase = true) ||
                        it.marca.contains(query, ignoreCase = true)
            }
        }
        notifyDataSetChanged()
    }
}