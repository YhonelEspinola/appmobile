package com.example.tiendazavaletaapp.recyclerwish

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.recyclermain.MasVendidos
import com.example.tiendazavaletaapp.recyclermain.MasVendidosViewHolder

class DeseadosAdapter (val list:List<Deseado>): RecyclerView.Adapter<DeseadosViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeseadosViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DeseadosViewHolder(inflater,parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: DeseadosViewHolder, position: Int) {
        val deseado=list[position]
        holder.bind(deseado)
    }
}