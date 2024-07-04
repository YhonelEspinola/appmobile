package com.example.tiendazavaletaapp.detallePedidoPce

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.carrito.Carrito

class PedidoPceAdapter(): RecyclerView.Adapter<PedidoPceViewHolder>() {

    private var list= emptyList<Carrito>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidoPceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PedidoPceViewHolder(inflater,parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PedidoPceViewHolder, position: Int) {
        val pce=list[position]
        holder.bind(pce)
    }

    fun setDatos(datos: List<Carrito>) {
        list = datos
        notifyDataSetChanged()
    }
}