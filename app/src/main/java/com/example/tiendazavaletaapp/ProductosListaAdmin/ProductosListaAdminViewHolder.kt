package com.example.tiendazavaletaapp.ProductosListaAdmin

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.R
import com.example.tiendazavaletaapp.editProductos.EditProductosActivity
import com.example.tiendazavaletaapp.recyclermain.MasVendidos
import com.squareup.picasso.Picasso

class ProductosListaAdminViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_productos_listadmin,viewGroup,false)) {

    private var textTitulo: TextView?=null
    private var textMarca: TextView?=null
    private var textCategoria: TextView?=null
    private var textCodigo: TextView?=null
    private var textPrecio: TextView?=null
    private var imgProducto: ImageView?=null
    private var btnEdit: Button? = null

    init{
        textTitulo = itemView.findViewById(R.id.textTitulo)
        textMarca = itemView.findViewById(R.id.textMarca)
        textCategoria= itemView.findViewById(R.id.textCategoria)
        textCodigo= itemView.findViewById(R.id.textCodigo)
        textPrecio = itemView.findViewById(R.id.textPrecio)
        imgProducto=itemView.findViewById(R.id.imgProducto)


        btnEdit = itemView.findViewById(R.id.btnEdit)


    }
    fun bind(productosListaAdmin: ProductosListaAdmin){
        textTitulo?.text=productosListaAdmin.nProducto
        textMarca?.text=productosListaAdmin.marca
        textCategoria?.text=productosListaAdmin.categoria
        textCodigo?.text=productosListaAdmin.codProducto
        textPrecio?.text=String.format("%.2f", productosListaAdmin.precio)
        Picasso.get().load(productosListaAdmin.imgProducto).into(imgProducto)

        btnEdit?.setOnClickListener {
            val intent = Intent(itemView.context, EditProductosActivity::class.java).apply {
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