package com.example.tiendazavaletaapp.recyclerwish

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.R
import com.example.tiendazavaletaapp.recyclermain.MasVendidos

class DeseadosViewHolder (inflater: LayoutInflater, viewGroup: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_wish,viewGroup,false)) {

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
    fun bind(deseado: Deseado){
        textMarca?.text=deseado.marca
        textTitulo?.text=deseado.titulo
        textCategoria?.text=deseado.categoria
        textPrecio?.text=String.format("%.2f", deseado.precio)
        imgProducto?.setImageResource(deseado.imagen)

    }

}
