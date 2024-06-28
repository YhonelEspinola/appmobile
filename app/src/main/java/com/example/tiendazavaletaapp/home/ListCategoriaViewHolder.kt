package com.example.tiendazavaletaapp.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.ProductosListaAdmin.ProductosListaAdmin
import com.example.tiendazavaletaapp.R
import com.example.tiendazavaletaapp.detalleProducto.DetalleProductoActivity
import com.squareup.picasso.Picasso

class ListCategoriaViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_producto,viewGroup,false)) {

    private var textTitulo: TextView?=null
    private var textMarca: TextView?=null
    private var textCategoria: TextView?=null
    private var textCodigo: TextView?=null
    private var textPrecio: TextView?=null
    private var imgProducto: ImageView?=null
    private var itemProducto: LinearLayout?=null
    private var textStock: TextView?=null
    private var textDescripcion: TextView?=null

    init{
        textTitulo = itemView.findViewById(R.id.textTitulo)
        textMarca = itemView.findViewById(R.id.textMarca)
        textCategoria= itemView.findViewById(R.id.textCategoria)
        textCodigo= itemView.findViewById(R.id.textCodigo)
        textPrecio = itemView.findViewById(R.id.textPrecio)
        imgProducto=itemView.findViewById(R.id.imgProducto)
        textStock=itemView.findViewById(R.id.textStock)
        textDescripcion=itemView.findViewById(R.id.textDescripcion)

        itemProducto=itemView.findViewById(R.id.itemProducto)

    }
    fun bind(productosListaAdmin: ProductosListaAdmin){
        textTitulo?.text=productosListaAdmin.nProducto
        textMarca?.text=productosListaAdmin.marca
        textCategoria?.text=productosListaAdmin.categoria
        textCodigo?.text=productosListaAdmin.codProducto
        textPrecio?.text=String.format("%.2f", productosListaAdmin.precio)
        textDescripcion?.text=productosListaAdmin.descripcion
        textStock?.text= productosListaAdmin.stock.toString()
        Picasso.get().load(productosListaAdmin.imgProducto).into(imgProducto)

        itemProducto?.setOnClickListener {
            val intent = Intent(itemView.context, DetalleProductoActivity::class.java).apply {
                putExtra("nProducto", productosListaAdmin.nProducto)
                putExtra("marca", productosListaAdmin.marca)
                putExtra("categoria", productosListaAdmin.categoria)
                putExtra("codigo", productosListaAdmin.codProducto)
                putExtra("precio", productosListaAdmin.precio)
                putExtra("imgProducto", productosListaAdmin.imgProducto)
                putExtra("stock", productosListaAdmin.stock)
                putExtra("descripcion", productosListaAdmin.descripcion)
            }
            itemView.context.startActivity(intent)
        }

    }
}