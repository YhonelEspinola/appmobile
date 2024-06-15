package com.example.tiendazavaletaapp.buscar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class BuscarAdapter (val list:List<Buscar>): RecyclerView.Adapter<BuscarViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuscarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BuscarViewHolder(inflater,parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BuscarViewHolder, position: Int) {
        val buscar=list[position]
        holder.bind(buscar)
    }
}