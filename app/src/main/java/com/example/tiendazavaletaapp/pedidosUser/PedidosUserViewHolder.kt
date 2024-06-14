package com.example.tiendazavaletaapp.pedidosUser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.R
import com.example.tiendazavaletaapp.detallePedidoUser.DetallePedidoUserFragment
import com.example.tiendazavaletaapp.pedidos.Pedidos

class PedidosUserViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_historial_pedidos,viewGroup,false)) {

    private var textTipoPedido: TextView?=null
    private var textDireccion: TextView?=null
    private var textFechaCompra: TextView?=null
    private var textCodPedido: TextView?=null
    private var textTotal: TextView?=null
    private var detallePedidoLayout: LinearLayout? = null

    init {
        textTipoPedido = itemView.findViewById(R.id.tipoPedido)
        textDireccion = itemView.findViewById(R.id.textDireccion)
        textFechaCompra = itemView.findViewById(R.id.fecha_compra)
        textCodPedido = itemView.findViewById(R.id.cod_pedido)
        textTotal = itemView.findViewById(R.id.total_amount)

        detallePedidoLayout = itemView.findViewById(R.id.DetallePedido)

        detallePedidoLayout?.setOnClickListener {
            // Aquí manejas la navegación al nuevo fragmento
            val context = itemView.context
            val fragment = DetallePedidoUserFragment.newInstance()
            val fragmentManager = (context as AppCompatActivity).supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.fragment_menu, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

    fun bind(pedidosUser: PedidosUser){
        textTipoPedido?.text= pedidosUser.getEntrega(pedidosUser.EntregaTienda)
        textDireccion?.text=pedidosUser.Direccion
        textFechaCompra?.text=pedidosUser.FechaCompra
        textCodPedido?.text=pedidosUser.CodPedido
        textTotal?.text= pedidosUser.Total.toString()
    }
}