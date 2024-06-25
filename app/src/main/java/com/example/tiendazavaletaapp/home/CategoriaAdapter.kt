package com.example.tiendazavaletaapp.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.R

class CategoriaAdapter(val list: List<Categoria>,private val listener: OnCategoriaClickListener): RecyclerView.Adapter<CategoriaViewHolder>() {
    private var selectedPosition = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CategoriaViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
        val categoria = list[position]
        var pos:Int = position
        holder.bind(categoria, listener)

        if (pos == selectedPosition) {
            holder.btnCategoria?.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.bottom))
            holder.btnCategoria?.layoutParams?.height= holder.itemView.context.resources.getDimensionPixelSize(R.dimen.categoria_select)
        } else {
            holder.btnCategoria?.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.marca))
            holder.btnCategoria?.layoutParams?.height= holder.itemView.context.resources.getDimensionPixelSize(R.dimen.categoria_normal)
        }
        holder.btnCategoria?.setOnClickListener {
            val previousPosition = selectedPosition
            selectedPosition = pos
            notifyItemChanged(previousPosition)
            notifyItemChanged(selectedPosition)
            listener.onCategoriaClick(categoria)
        }
    }
}