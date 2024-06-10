package com.example.tiendazavaletaapp.carrito

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.R

class CarritoViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup): RecyclerView.ViewHolder(inflater.inflate(R.layout.item_carrito,viewGroup,false)) {

    private var imgCarrito : ImageView? = null
    private var textNombre : TextView? = null
    private var textMarca : TextView? = null
    private var textPrecio : TextView? = null
    private var textCantidad : TextView? = null

    init {
        imgCarrito = itemView.findViewById(R.id.imgCarrito)
        textNombre = itemView.findViewById(R.id.textNombre)
        textMarca = itemView.findViewById(R.id.textMarca)
        textPrecio = itemView.findViewById(R.id.textPrecio)
        textCantidad = itemView.findViewById(R.id.textCantidad)
    }

    fun bind(carrito: Carrito){
        textNombre?.text= carrito.nombre
        textMarca?.text=carrito.marca
        textPrecio?.text=String.format("%.2f", carrito.precio)
        textCantidad?.text = carrito.cantidad.toString()
        imgCarrito?.setImageResource(carrito.imgCarrito)
    }

}