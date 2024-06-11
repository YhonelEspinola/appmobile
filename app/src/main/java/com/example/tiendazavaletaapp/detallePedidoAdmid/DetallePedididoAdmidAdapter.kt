package com.example.tiendazavaletaapp.detallePedidoAdmid

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class DetallePedididoAdmidAdapter(val list: List<DetallePedidoAdmid>):RecyclerView.Adapter<DetallePedidoAdmidViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetallePedidoAdmidViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DetallePedidoAdmidViewHolder(inflater,parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: DetallePedidoAdmidViewHolder, position: Int) {
        val detallePedidosAdmid=list[position]
        holder.bind(detallePedidosAdmid)
    }

}