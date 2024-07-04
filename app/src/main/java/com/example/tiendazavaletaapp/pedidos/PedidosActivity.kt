package com.example.tiendazavaletaapp.pedidos

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.Perfil.PerfilViewModel
import com.example.tiendazavaletaapp.R
import com.example.tiendazavaletaapp.gestionAdmid.GestionAdminActivity

class PedidosActivity:AppCompatActivity(){
    private lateinit var viewModel: PedidoAdminViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedidos)

        viewModel = ViewModelProvider(this)[PedidoAdminViewModel::class.java]
        val recyclerPedidos = findViewById<RecyclerView>(R.id.recyclerPedidos)

        val adapterP = PedidosAdapter()
        recyclerPedidos.adapter = adapterP
        recyclerPedidos.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        viewModel.listPedidos()
        viewModel.listPedidos.observe(this) {

            if (it.isNotEmpty()) {
                adapterP.setDatos(it)
            }

        }

        val btnButtonAtras = findViewById<ImageView>(R.id.btnbuAtras)
        btnButtonAtras.setOnClickListener {
            val intent = Intent(this, GestionAdminActivity::class.java)
            startActivity(intent)
        }
    }
}