package com.example.tiendazavaletaapp.carrito

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CarritoAdapter(private val list: MutableList<Carrito>) : RecyclerView.Adapter<CarritoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarritoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CarritoViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CarritoViewHolder, position: Int) {
        val carrito = list[position]
        holder.bind(carrito, this)
    }

    fun updateList(newList: List<Carrito>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }
}