package com.example.tiendazavaletaapp.detallePedidoAdmid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.R

class DetallePedidoAdmidActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_pedido_admid)


        val recyclerDetallesPedidosAdmid = findViewById<RecyclerView>(R.id.recyclerdetalles)

        val listDetallePedidosAdmid = listOf<DetallePedidoAdmid>(
            DetallePedidoAdmid("Cuaderno justus",10,"ss88",3.0),
            DetallePedidoAdmid("Cuaderno college",10,"ss88",5.0),
        )

        val adapterD = DetallePedididoAdmidAdapter(listDetallePedidosAdmid)
        recyclerDetallesPedidosAdmid.adapter=adapterD
        recyclerDetallesPedidosAdmid.layoutManager=LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}