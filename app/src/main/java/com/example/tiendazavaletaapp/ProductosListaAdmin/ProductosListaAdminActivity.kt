package com.example.tiendazavaletaapp.ProductosListaAdmin

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.R

class ProductosListaAdminActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_productos_listadmin)

        val recyclerProductosListAdmin =findViewById<RecyclerView>(R.id.recyclerProductosListAdmin)
        val listProductos = listOf<ProductosListaAdmin>(
            ProductosListaAdmin("Titulo","Marca: marca","Cateogria: categoria","Codigo: 963852",0.00,R.drawable.img),
            ProductosListaAdmin("Titulo","Marca: marca","Cateogria: categoria","Codigo: 963852",0.00,R.drawable.img),
            ProductosListaAdmin("Titulo","Marca: marca","Cateogria: categoria","Codigo: 963852",0.00,R.drawable.img),
            ProductosListaAdmin("Titulo","Marca: marca","Cateogria: categoria","Codigo: 963852",0.00,R.drawable.img),
            ProductosListaAdmin("Titulo","Marca: marca","Cateogria: categoria","Codigo: 963852",0.00,R.drawable.img),
            ProductosListaAdmin("Titulo","Marca: marca","Cateogria: categoria","Codigo: 963852",0.00,R.drawable.img)
        )
        val adapterPLA = ProductosListaAdminAdapter(listProductos)
        recyclerProductosListAdmin.adapter=adapterPLA
        recyclerProductosListAdmin.layoutManager= LinearLayoutManager(this)

    }
}