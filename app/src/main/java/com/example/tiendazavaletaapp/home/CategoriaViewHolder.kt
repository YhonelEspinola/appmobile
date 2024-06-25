package com.example.tiendazavaletaapp.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.R

class CategoriaViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup): RecyclerView.ViewHolder(inflater.inflate(
    R.layout.item_categorias,viewGroup,false)) {

    private var btnCategoria : Button? = null


    init {
        btnCategoria = itemView.findViewById(R.id.btnCategoria)
    }

    fun bind(categoria: Categoria, listener: OnCategoriaClickListener){
        btnCategoria?.text= categoria.categoria

        btnCategoria?.setOnClickListener {
            listener.onCategoriaClick(categoria)
        }
    }

}