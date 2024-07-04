package com.example.tiendazavaletaapp.detallePedidoUser

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.carrito.Carrito

class DetallePedididoUserAdapter():RecyclerView.Adapter<DetallePedidoUserViewHolder>() {
    private var list= emptyList<DetallePedidoUser>()
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

    fun setDatos(datos: List<DetallePedidoUser>) {
        list = datos
        notifyDataSetChanged()
    }
}