package com.example.tiendazavaletaapp.detallePedidoUser

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.R

class DetallePedidoUserViewHolder(inflater: LayoutInflater, viewGroup : ViewGroup) :
RecyclerView.ViewHolder(inflater.inflate(R.layout.item_detalle_pedido, viewGroup , false)) {

    private var textTitulo : TextView?=null
    private var textCantidad : TextView?=null
    private var textPrecioUnit : TextView?=null
    private var textCodigo : TextView?=null


    init {
        textTitulo = itemView.findViewById(R.id.textTituloDetallePedido)
        textCantidad = itemView.findViewById(R.id.textCantidad)
        textPrecioUnit = itemView.findViewById(R.id.textPrecioUnitario)
        textCodigo = itemView.findViewById(R.id.textCodigo)

    }

    fun bind(detallePedido:DetallePedidoUser){
        textTitulo?.text=detallePedido.nProducto
        textCantidad?.text=detallePedido.cantidad.toString()
        textPrecioUnit?.text=String.format("%.2f", detallePedido.precio)
        textCodigo?.text=detallePedido.codProducto


    }

}