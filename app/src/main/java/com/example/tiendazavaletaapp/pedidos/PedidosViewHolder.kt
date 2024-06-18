package com.example.tiendazavaletaapp.pedidos

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.R
import com.example.tiendazavaletaapp.detallePedidoAdmid.DetallePedidoAdmid
import com.example.tiendazavaletaapp.detallePedidoAdmid.DetallePedidoAdmidActivity
import com.example.tiendazavaletaapp.editProductos.EditProductosActivity


class PedidosViewHolder (inflater: LayoutInflater, viewGroup: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_pedidos,viewGroup,false)){
    private var textCorreoCliente: TextView?=null
    private var textEntregaTienda: TextView?=null
    private var textFechaCompra: TextView?=null
    private var textCodPedido: TextView?=null
    private var textTotal: TextView?=null
    private var cardView: CardView? = null



    init {
        textCorreoCliente = itemView.findViewById(R.id.textCorreoCliente)
        textEntregaTienda = itemView.findViewById(R.id.textEntregaTienda)
        textFechaCompra = itemView.findViewById(R.id.textFechaCompra)
        textCodPedido = itemView.findViewById(R.id.textCodPedido)
        textTotal = itemView.findViewById(R.id.textTotal)

        cardView = itemView.findViewById(R.id.linerId)
        cardView?.setOnClickListener {
            val intent = Intent(itemView.context, DetallePedidoAdmidActivity::class.java)
            itemView.context.startActivity(intent)
        }
    }

    fun bind(pedidos:Pedidos){
        textCorreoCliente?.text =pedidos.CorreoUser
        textEntregaTienda?.text= pedidos.getEntrega(pedidos.EntregaTienda)
        textFechaCompra?.text=pedidos.FechaCompra
        textCodPedido?.text=pedidos.CodPedido
        textTotal?.text= pedidos.Total.toString()
    }
}