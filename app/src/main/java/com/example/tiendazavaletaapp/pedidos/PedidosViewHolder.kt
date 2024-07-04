package com.example.tiendazavaletaapp.pedidos

import android.content.Intent
import android.icu.text.SimpleDateFormat
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
import com.example.tiendazavaletaapp.pedidosUser.PedidosUser


class PedidosViewHolder (inflater: LayoutInflater, viewGroup: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_pedidos,viewGroup,false)){

    private var textCorreoCliente: TextView?=null
    private var tipoPedido: TextView?=null
    private var textDireccion: TextView?=null
    private var fecha_realizado: TextView?=null
    private var textCodPedido: TextView?=null
    private var textTotal: TextView?=null
    private var detallePedidoLayout: LinearLayout? = null

    init {
        textCorreoCliente = itemView.findViewById(R.id.textCorreoCliente)
        tipoPedido = itemView.findViewById(R.id.tipoPedido)
        textDireccion = itemView.findViewById(R.id.textDireccion)
        fecha_realizado = itemView.findViewById(R.id.fecha_realizado)
        textCodPedido = itemView.findViewById(R.id.cod_pedido)
        textTotal = itemView.findViewById(R.id.total_amount)

        detallePedidoLayout = itemView.findViewById(R.id.DetallePedido)

    }

    fun bind(pedidos:PedidosUser){
        textCorreoCliente?.text =pedidos.email
        tipoPedido?.text= pedidos.tipodecompra
        textDireccion?.text=pedidos.direccion
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        val fechaFormateada = pedidos.fecha?.toDate()?.let { dateFormat.format(it) } ?: ""
        fecha_realizado?.text=fechaFormateada
        textCodPedido?.text= pedidos.idpedido
        textTotal?.text=pedidos.total

        detallePedidoLayout?.setOnClickListener {
            val intent = Intent(itemView.context, DetallePedidoAdmidActivity::class.java).apply {
                putExtra("idpedido", pedidos.idpedido)
                putExtra("cantTotalProduct", pedidos.cantTotalProduct)
                putExtra("direccion", pedidos.direccion)
                putExtra("dni", pedidos.dni)
                putExtra("email", pedidos.email)
                putExtra("fecha", fechaFormateada)
                putExtra("fechaestimada", pedidos.fechaestimada)
                putExtra("nombreApe", pedidos.nombreApe)
                putExtra("referencia", pedidos.referencia)
                putExtra("tipodecompra", pedidos.tipodecompra)
                putExtra("total", pedidos.total)
                putExtra("ubicacion", pedidos.ubicacion)
                putExtra("uid", pedidos.uid)
            }
            itemView.context.startActivity(intent)
        }

    }
}