package com.example.tiendazavaletaapp.detallePedidoUser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.R

class DetallePedidoUserActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_pedido_users)


        val recyclerDetallesPedidosUser = findViewById<RecyclerView>(R.id.recyclerdetalles)

        val listDetallePedidosUser = listOf<DetallePedidoUser>(
            DetallePedidoUser("Cuaderno justus",10,"ss88",3.0),
            DetallePedidoUser("Cuaderno college",12,"ss88",5.0),
        )

        val adapterD = DetallePedididoUserAdapter(listDetallePedidosUser)
        recyclerDetallesPedidosUser.adapter=adapterD
        recyclerDetallesPedidosUser.layoutManager=LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}