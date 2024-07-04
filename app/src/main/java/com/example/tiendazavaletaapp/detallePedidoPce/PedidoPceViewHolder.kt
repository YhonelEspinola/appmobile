package com.example.tiendazavaletaapp.detallePedidoPce

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.R
import com.example.tiendazavaletaapp.carrito.Carrito
import com.google.firebase.firestore.FirebaseFirestore

class PedidoPceViewHolder (inflater: LayoutInflater, viewGroup: ViewGroup) : RecyclerView.ViewHolder(inflater.inflate(
    R.layout.item_pedido_pce, viewGroup, false)) {

    private var txtProducto: TextView? = null
    private var totalProducto: TextView? = null
    private var textPrecio: TextView? = null

    private val db = FirebaseFirestore.getInstance()

    init {
        txtProducto = itemView.findViewById(R.id.txtProducto)
        totalProducto = itemView.findViewById(R.id.totalProducto)
        textPrecio = itemView.findViewById(R.id.textPrecio)
    }

    fun bind(carrito: Carrito) {
        txtProducto?.text = carrito.nombre
        textPrecio?.text = String.format("%.2f", carrito.precio)
        totalProducto?.text = carrito.cantidad.toString()
    }
}