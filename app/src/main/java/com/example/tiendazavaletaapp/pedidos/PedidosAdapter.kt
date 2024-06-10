package com.example.tiendazavaletaapp.pedidos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.recyclermain.MasVendidosViewHolder

class PedidosAdapter (val list:List<Pedidos>):RecyclerView.Adapter<PedidosViewHolder>(){
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

}