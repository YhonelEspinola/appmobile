package com.example.tiendazavaletaapp.detallePedidoAdmid

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.R

class DetallePedidoAdmidViewHolder(inflater: LayoutInflater, viewGroup : ViewGroup) :
RecyclerView.ViewHolder(inflater.inflate(R.layout.item_detalle_pedido_admid, viewGroup , false)) {

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

    fun bind(detallePedido:DetallePedidoAdmid){
        textTitulo?.text=detallePedido.TituloItem
        textCantidad?.text=detallePedido.Cantidad.toString()
        textPrecioUnit?.text=String.format("%.2f", detallePedido.PrecioUnitario)
        textCodigo?.text=detallePedido.CodPedido


    }

}