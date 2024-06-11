package com.example.tiendazavaletaapp.recyclermain

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CategoriaAdapter(val list: List<Categoria>): RecyclerView.Adapter<CategoriaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CategoriaViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
        val categoria = list[position]
        holder.bind(categoria)
    }
}