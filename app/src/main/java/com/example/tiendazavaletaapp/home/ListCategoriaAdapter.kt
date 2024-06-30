package com.example.tiendazavaletaapp.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.ProductosListaAdmin.ProductosListaAdmin


class ListCategoriaAdapter(): RecyclerView.Adapter<ListCategoriaViewHolder>() {
    private var list= emptyList<ProductosListaAdmin>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCategoriaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ListCategoriaViewHolder(inflater,parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ListCategoriaViewHolder, position: Int) {
        val productosListaAdmin=list[position]
        holder.bind(productosListaAdmin)
    }

    fun setDatos(datos: List<ProductosListaAdmin>) {
        list = datos
        notifyDataSetChanged()
    }
}