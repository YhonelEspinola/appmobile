package com.example.tiendazavaletaapp.buscar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.ProductosListaAdmin.ProductosListaAdmin


class BuscarAdapter (var list:List<ProductosListaAdmin>): RecyclerView.Adapter<BuscarViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuscarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BuscarViewHolder(inflater,parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BuscarViewHolder, position: Int) {
        val buscar=list[position]
        holder.bind(buscar)
    }

    fun setDatos(datos: List<ProductosListaAdmin>) {
        this.list = datos
        notifyDataSetChanged()
    }
}