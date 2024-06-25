package com.example.tiendazavaletaapp.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.R

class ProductosHomeViewHolder (inflater: LayoutInflater, viewGroup: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_p_masvendido,viewGroup,false)) {

    private var textMarca: TextView?=null
    private var textTitulo: TextView?=null
    private var textCategoria: TextView?=null
    private var textPrecio: TextView?=null
    private var imgProducto: ImageView?=null


    init{
        textMarca = itemView.findViewById(R.id.textMarca)
        textTitulo = itemView.findViewById(R.id.textTitulo)
        textCategoria= itemView.findViewById(R.id.textCategoria)
        textPrecio = itemView.findViewById(R.id.textPrecio)
        imgProducto=itemView.findViewById(R.id.imgProducto)

    }
    fun bind(masVendidos:ProductosHome){
        textMarca?.text=masVendidos.marca
        textTitulo?.text=masVendidos.titulo
        textCategoria?.text=masVendidos.categoria
        textPrecio?.text=String.format("%.2f", masVendidos.precio)
        imgProducto?.setImageResource(masVendidos.imagen)

    }

}