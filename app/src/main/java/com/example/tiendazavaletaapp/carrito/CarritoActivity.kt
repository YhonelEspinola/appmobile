package com.example.tiendazavaletaapp.carrito

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.R

class CarritoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)

        val recyclerCarrito =findViewById<RecyclerView>(R.id.recyclerCarrito)

        val listCarrito = listOf<Carrito>(
            Carrito("La Odisea","...",12.50,2,R.drawable.logo),
            Carrito("La Odisea","...",12.50,2,R.drawable.logo),
            Carrito("La Odisea","...",12.50,2,R.drawable.logo),
            Carrito("La Odisea","...",12.50,2,R.drawable.logo),
            Carrito("La Odisea","...",12.50,2,R.drawable.logo),
            Carrito("La Odisea","...",12.50,2,R.drawable.logo)
        )

        val adapter = CarritoAdapter(listCarrito)
        recyclerCarrito.adapter = adapter
        recyclerCarrito.layoutManager = LinearLayoutManager(this)
    }
}