package com.example.tiendazavaletaapp.pedidosUser

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.R
import com.example.tiendazavaletaapp.detallePedidoUser.DetallePedidoUserFragment

class PedidosUserViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_historial_pedidos,viewGroup,false)) {

    private var tipoPedido: TextView?=null
    private var textDireccion: TextView?=null
    private var fecha_realizado: TextView?=null
    private var textCodPedido: TextView?=null
    private var textTotal: TextView?=null
    private var detallePedidoLayout: LinearLayout? = null

    init {
        tipoPedido = itemView.findViewById(R.id.tipoPedido)
        textDireccion = itemView.findViewById(R.id.textDireccion)
        fecha_realizado = itemView.findViewById(R.id.fecha_realizado)
        textCodPedido = itemView.findViewById(R.id.cod_pedido)
        textTotal = itemView.findViewById(R.id.total_amount)

        detallePedidoLayout = itemView.findViewById(R.id.DetallePedido)

    }

    fun bind(pedidosUser: PedidosUser){
        tipoPedido?.text= pedidosUser.tipodecompra
        textDireccion?.text=pedidosUser.direccion
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        val fechaFormateada = pedidosUser.fecha?.toDate()?.let { dateFormat.format(it) } ?: ""
        fecha_realizado?.text = fechaFormateada
        textCodPedido?.text=pedidosUser.idpedido
        textTotal?.text= pedidosUser.total

        detallePedidoLayout?.setOnClickListener {
            val context = itemView.context
            val fragment = DetallePedidoUserFragment.newInstance()

            val bundle = Bundle()
            bundle.putString("pedidoId", pedidosUser.idpedido)
            bundle.putString("direccion", pedidosUser.direccion)
            bundle.putString("fecha", fechaFormateada)
            bundle.putString("total", pedidosUser.total)
            bundle.putString("cantTotalProduct", pedidosUser.cantTotalProduct)
            bundle.putString("fechaestimada", pedidosUser.fechaestimada)
            bundle.putString("nombreApe", pedidosUser.nombreApe)
            bundle.putString("tipodecompra", pedidosUser.tipodecompra)
            bundle.putString("dni", pedidosUser.dni)

            fragment.arguments = bundle

            val fragmentManager = (context as AppCompatActivity).supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.fragment_menu, fragment)
                .addToBackStack(null)
                .commit()
        }

    }
}