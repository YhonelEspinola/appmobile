package com.example.tiendazavaletaapp.pedidosUser

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.R
import com.example.tiendazavaletaapp.pedidos.Pedidos

class PedidosUserViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_historial_pedidos,viewGroup,false)) {

    private var textEntregaTienda: TextView?=null
    private var textFechaCompra: TextView?=null
    private var textCodPedido: TextView?=null
    private var textTotal: TextView?=null

    init {
        textEntregaTienda = itemView.findViewById(R.id.recogo_tienda)
        textFechaCompra = itemView.findViewById(R.id.fecha_compra)
        textCodPedido = itemView.findViewById(R.id.cod_pedido)
        textTotal = itemView.findViewById(R.id.total_amount)
    }

    fun bind(pedidosUser: PedidosUser){
        textEntregaTienda?.text= pedidosUser.getEntrega(pedidosUser.EntregaTienda)
        textFechaCompra?.text=pedidosUser.FechaCompra
        textCodPedido?.text=pedidosUser.CodPedido
        textTotal?.text= pedidosUser.Total.toString()
    }
}