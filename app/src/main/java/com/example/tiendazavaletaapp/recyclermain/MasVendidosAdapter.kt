package com.example.tiendazavaletaapp.recyclermain

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MasVendidosAdapter(val list:List<MasVendidos>): RecyclerView.Adapter<MasVendidosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MasVendidosViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MasVendidosViewHolder(inflater,parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MasVendidosViewHolder, position: Int) {
        val masVendidos=list[position]
        holder.bind(masVendidos)
    }
}