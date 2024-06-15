package com.example.tiendazavaletaapp.buscar

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.R
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView

class BuscarViewHolder (inflater: LayoutInflater, viewGroup: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_search,viewGroup ,false)) {

    private var textMarca: TextView? = null
    private var textTitulo: TextView? = null
    private var textCategoria: TextView? = null
    private var textPrecio: TextView? = null
    private var imgProducto: ImageView? = null


    init {
        textMarca = itemView.findViewById(R.id.textMarca)
        textTitulo = itemView.findViewById(R.id.textTitulo)
        textCategoria = itemView.findViewById(R.id.textCategoria)
        textPrecio = itemView.findViewById(R.id.textPrecio)
        imgProducto = itemView.findViewById(R.id.imgProducto)

    }

    fun bind(buscar: Buscar) {
        textMarca?.text = buscar.marca
        textTitulo?.text = buscar.titulo
        textCategoria?.text = buscar.categoria
        textPrecio?.text = String.format("%.2f", buscar.precio)
        imgProducto?.setImageResource(buscar.imagen)
    }
}