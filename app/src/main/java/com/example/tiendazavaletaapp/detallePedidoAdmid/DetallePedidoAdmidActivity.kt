package com.example.tiendazavaletaapp.detallePedidoAdmid

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.R
import com.example.tiendazavaletaapp.detallePedidoUser.DetallePedididoUserAdapter
import com.example.tiendazavaletaapp.detallePedidoUser.DetallePedidoUserViewModel

class DetallePedidoAdmidActivity:AppCompatActivity() {
    private lateinit var viewModel: DetallePedidoUserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_pedido_admid)

        viewModel = ViewModelProvider(this)[DetallePedidoUserViewModel::class.java]
        val recyclerDetallesPedidosAdmid = findViewById<RecyclerView>(R.id.recyclerdetalles)
        val textCorreoCliente = findViewById<TextView>(R.id.textCorreoCliente)
        val tipoPedido = findViewById<TextView>(R.id.tipoPedido)
        val textDireccion = findViewById<TextView>(R.id.textDireccion)
        val textFechaCompra = findViewById<TextView>(R.id.textFechaCompra)
        val textCodPedido = findViewById<TextView>(R.id.textCodPedido)
        val textNombreApe = findViewById<TextView>(R.id.textNombreApe)
        val textDni = findViewById<TextView>(R.id.textDni)
        val textCantProductosTotal = findViewById<TextView>(R.id.textCantProductosTotal)
        val textTotal = findViewById<TextView>(R.id.textTotal)
        val textFechaEstimada = findViewById<TextView>(R.id.textFechaEstimada)
        val textUbicación = findViewById<TextView>(R.id.textUbicación)
        val textReferencia = findViewById<TextView>(R.id.textReferencia)
        val textUserId = findViewById<TextView>(R.id.textUserId)

        val adapterP = DetallePedididoUserAdapter()
        recyclerDetallesPedidosAdmid.adapter=adapterP
        recyclerDetallesPedidosAdmid.layoutManager=LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        textCorreoCliente.text = intent.getStringExtra("email")
        tipoPedido.text = intent.getStringExtra("tipodecompra")
        textDireccion.text = intent.getStringExtra("direccion")
        textFechaCompra.text = intent.getStringExtra("fecha")
        textCodPedido.text = intent.getStringExtra("idpedido")
        textNombreApe.text = intent.getStringExtra("nombreApe")
        textDni.text = intent.getStringExtra("dni")
        textCantProductosTotal.text = intent.getStringExtra("cantTotalProduct")
        textTotal.text = intent.getStringExtra("total")
        textFechaEstimada.text = intent.getStringExtra("fechaestimada")
        textUbicación.text = intent.getStringExtra("ubicacion")
        textReferencia.text = intent.getStringExtra("referencia")
        textUserId.text = intent.getStringExtra("uid")

        val pedidoId=intent.getStringExtra("idpedido")

        viewModel.listDetallePedidos(pedidoId.toString())
        viewModel.listDetallePedidos.observe(this) {

            if (it.isNotEmpty()) {
                adapterP.setDatos(it)
            }

        }
    }
}