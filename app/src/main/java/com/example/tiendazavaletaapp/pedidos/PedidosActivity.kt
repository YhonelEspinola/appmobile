package com.example.tiendazavaletaapp.pedidos

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.R
import com.example.tiendazavaletaapp.gestionAdmid.GestionAdminActivity

class PedidosActivity:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedidos)

        val recyclerPedidos = findViewById<RecyclerView>(R.id.recyclerPedidos)

        val listPedidos = listOf<Pedidos>(
            //Pedidos("Carlitos@gmail.com",true,"01/01/2024","hhh111",20.00),
            Pedidos("Carlos@gmail.com", false, "07/01/2024", "hhf222", 25.00),
            Pedidos("Sergio@gmail.com", true, "01/02/2024", "hhf222", 45.00),
        )

        val adapterP = PedidosAdapter(listPedidos)
        recyclerPedidos.adapter = adapterP
        recyclerPedidos.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val btnButtonAtras = findViewById<ImageView>(R.id.btnbuAtras)
        btnButtonAtras.setOnClickListener {
            val intent = Intent(this, GestionAdminActivity::class.java)
            startActivity(intent)
        }
    }
}