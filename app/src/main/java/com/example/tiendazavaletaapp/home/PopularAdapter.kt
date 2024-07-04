package com.example.tiendazavaletaapp.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.ProductosListaAdmin.ProductosListaAdmin

class PopularAdapter(): RecyclerView.Adapter<PopularViewHolder>() {
    private var list= emptyList<ProductosListaAdmin>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PopularViewHolder(inflater,parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val popular=list[position]
        holder.bind(popular)
    }

    fun setDatos(datos: List<ProductosListaAdmin>) {
        this.list = datos
        notifyDataSetChanged()
    }
}