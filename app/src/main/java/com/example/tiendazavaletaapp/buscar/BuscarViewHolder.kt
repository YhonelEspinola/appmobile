package com.example.tiendazavaletaapp.buscar

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.R
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tiendazavaletaapp.ProductosListaAdmin.ProductosListaAdmin
import com.example.tiendazavaletaapp.detalleProducto.DetalleProductoActivity
import com.squareup.picasso.Picasso

class BuscarViewHolder (inflater: LayoutInflater, viewGroup: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_producto,viewGroup ,false)) {

    private var textTitulo: TextView?=null
    private var textMarca: TextView?=null
    private var textCategoria: TextView?=null
    private var textCodigo: TextView?=null
    private var textPrecio: TextView?=null
    private var imgProducto: ImageView?=null
    private var itemProducto: LinearLayout?=null
    private var textDescripcion: TextView?=null
    private var textStock: TextView?=null


    init {
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

    fun bind(buscar: ProductosListaAdmin) {
        textTitulo?.text=buscar.nProducto
        textMarca?.text=buscar.marca
        textCategoria?.text=buscar.categoria
        textCodigo?.text=buscar.codProducto
        textPrecio?.text=String.format("%.2f", buscar.precio)
        textStock?.text= buscar.stock.toString()
        textDescripcion?.text=buscar.descripcion
        Picasso.get().load(buscar.imgProducto).into(imgProducto)

        itemProducto?.setOnClickListener {
            val intent = Intent(itemView.context, DetalleProductoActivity::class.java).apply {
                putExtra("nProducto", buscar.nProducto)
                putExtra("marca", buscar.marca)
                putExtra("categoria", buscar.categoria)
                putExtra("codigo", buscar.codProducto)
                putExtra("precio", buscar.precio)
                putExtra("imgProducto", buscar.imgProducto)
                putExtra("stock", buscar.stock)
                putExtra("descripcion", buscar.descripcion)
            }
            itemView.context.startActivity(intent)
        }
    }
}