package com.example.tiendazavaletaapp.recyclermain

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.R

class CategoriaViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup): RecyclerView.ViewHolder(inflater.inflate(
    R.layout.item_categorias,viewGroup,false)) {

    private var textCategoria : TextView? = null


    init {
        textCategoria = itemView.findViewById(R.id.textCategoria)
    }

    fun bind(categoria: Categoria){
        textCategoria?.text= categoria.categoria
    }
}