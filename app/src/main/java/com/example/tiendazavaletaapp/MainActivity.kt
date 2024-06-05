package com.example.tiendazavaletaapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.recyclermain.MasVendidos
import com.example.tiendazavaletaapp.recyclermain.MasVendidosAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val recyclerMasVendidos =findViewById<RecyclerView>(R.id.recyclerMasVendidos)
        val recyclerRecomendado =findViewById<RecyclerView>(R.id.recyclerRecomendado)
        val listProductos = listOf<MasVendidos>(
            MasVendidos("Marca","Titulo","Cateogria",0.00,R.drawable.img),
            MasVendidos("Marca","Titulo","Cateogria",0.00,R.drawable.img),
            MasVendidos("Marca","Titulo","Cateogria",0.00,R.drawable.img),
            MasVendidos("Marca","Titulo","Cateogria",0.00,R.drawable.img),
            MasVendidos("Marca","Titulo","Cateogria",0.00,R.drawable.img),
        )
        val adapterMV = MasVendidosAdapter(listProductos)
        recyclerMasVendidos.adapter=adapterMV
        recyclerMasVendidos.layoutManager=LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)

        val adapterR = MasVendidosAdapter(listProductos)
        recyclerRecomendado.adapter=adapterR
        recyclerRecomendado.layoutManager=LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)

    }
}