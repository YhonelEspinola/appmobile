package com.example.tiendazavaletaapp.detallePedidoUser

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class DetallePedididoUserAdapter(val list: List<DetallePedidoUser>):RecyclerView.Adapter<DetallePedidoUserViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetallePedidoUserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DetallePedidoUserViewHolder(inflater,parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: DetallePedidoUserViewHolder, position: Int) {
        val detallePedidosUser=list[position]
        holder.bind(detallePedidosUser)
    }

}