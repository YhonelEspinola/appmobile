package com.example.tiendazavaletaapp.pedidosUser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.R

class PedidosUserActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial_pedidos_user)

        val recyclerPedidosUser = findViewById<RecyclerView>(R.id.recyclerPedidosUser)

        val listPedidos = listOf<PedidosUser>(
            PedidosUser(true, "01/05/2024", "phh88", 20.0),
            PedidosUser(false, "08/05/2024", "phh99", 20.0)
        )


        val adapterC = PedidosUserAdapter(listPedidos)
        recyclerPedidosUser.adapter = adapterC
        recyclerPedidosUser.layoutManager = LinearLayoutManager(this)
    }
}