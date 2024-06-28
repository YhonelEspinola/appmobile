package com.example.tiendazavaletaapp.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.ProductosListaAdmin.ProductosListaAdmin
import com.example.tiendazavaletaapp.R
import com.example.tiendazavaletaapp.editProductos.EditProductosActivity
import com.squareup.picasso.Picasso

class ListCategoriaViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_wish,viewGroup,false)) {

    private var textTitulo: TextView?=null
    private var textMarca: TextView?=null
    private var textCategoria: TextView?=null
    private var textCodigo: TextView?=null
    private var textPrecio: TextView?=null
    private var imgProducto: ImageView?=null

    init{
        textTitulo = itemView.findViewById(R.id.textTitulo)
        textMarca = itemView.findViewById(R.id.textMarca)
        textCategoria= itemView.findViewById(R.id.textCategoria)
        textCodigo= itemView.findViewById(R.id.textCodigo)
        textPrecio = itemView.findViewById(R.id.textPrecio)
        imgProducto=itemView.findViewById(R.id.imgProducto)


    }
    fun bind(productosListaAdmin: ProductosListaAdmin){
        textTitulo?.text=productosListaAdmin.nProducto
        textMarca?.text=productosListaAdmin.marca
        textCategoria?.text=productosListaAdmin.categoria
        textCodigo?.text=productosListaAdmin.codProducto
        textPrecio?.text=String.format("%.2f", productosListaAdmin.precio)
        Picasso.get().load(productosListaAdmin.imgProducto).into(imgProducto)
    }
}