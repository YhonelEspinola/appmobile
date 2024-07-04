package com.example.tiendazavaletaapp.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.ProductosListaAdmin.ProductosListaAdmin
import com.example.tiendazavaletaapp.R
import com.example.tiendazavaletaapp.detalleProducto.DetalleProductoActivity
import com.squareup.picasso.Picasso

class PopularViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_producto_popular,viewGroup,false)) {

    private var textTitulo: TextView?=null
    private var textMarca: TextView?=null
    private var textCategoria: TextView?=null
    private var textCodigo: TextView?=null
    private var textPrecio: TextView?=null
    private var imgProducto: ImageView?=null
    private var itemProducto: LinearLayout?=null
    private var textDescripcion: TextView?=null
    private var textStock: TextView?=null

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

    fun bind(productoshome: ProductosListaAdmin){
        textTitulo?.text=productoshome.nProducto
        textMarca?.text=productoshome.marca
        textCategoria?.text=productoshome.categoria
        textCodigo?.text=productoshome.codProducto
        textPrecio?.text=String.format("%.2f", productoshome.precio)
        textStock?.text= productoshome.stock.toString()
        textDescripcion?.text=productoshome.descripcion
        imgProducto?.let{
            Picasso.get()
                .load(productoshome.imgProducto)
                .placeholder(R.drawable.img_carga)
                .error(R.drawable.img_error)
                .into(it)
        }

        itemProducto?.setOnClickListener {
            val intent = Intent(itemView.context, DetalleProductoActivity::class.java).apply {
                putExtra("nProducto", productoshome.nProducto)
                putExtra("marca", productoshome.marca)
                putExtra("categoria", productoshome.categoria)
                putExtra("codigo", productoshome.codProducto)
                putExtra("precio", productoshome.precio)
                putExtra("imgProducto", productoshome.imgProducto)
                putExtra("stock", productoshome.stock)
                putExtra("descripcion", productoshome.descripcion)
            }
            itemView.context.startActivity(intent)
        }

    }
}