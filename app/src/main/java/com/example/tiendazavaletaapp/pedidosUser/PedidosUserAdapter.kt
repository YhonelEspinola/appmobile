package com.example.tiendazavaletaapp.pedidosUser

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PedidosUserAdapter (): RecyclerView.Adapter<PedidosUserViewHolder>() {
    private var list= emptyList<PedidosUser>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidosUserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PedidosUserViewHolder(inflater,parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PedidosUserViewHolder, position: Int) {
        val pedidosUser=list[position]
        holder.bind(pedidosUser)
    }
    fun setDatos(datos: List<PedidosUser>) {
        list = datos
        notifyDataSetChanged()
    }
}