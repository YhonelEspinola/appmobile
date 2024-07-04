package com.example.tiendazavaletaapp.pedidos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.pedidosUser.PedidosUser

class PedidosAdapter ():RecyclerView.Adapter<PedidosViewHolder>(){
    private var list= emptyList<PedidosUser>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidosViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PedidosViewHolder(inflater,parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PedidosViewHolder, position: Int) {
        val pedidos=list[position]
        holder.bind(pedidos)
    }
    fun setDatos(datos: List<PedidosUser>) {
        list = datos
        notifyDataSetChanged()
    }
}