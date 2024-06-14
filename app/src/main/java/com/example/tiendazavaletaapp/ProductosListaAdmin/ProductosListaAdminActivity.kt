package com.example.tiendazavaletaapp.ProductosListaAdmin

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.R
import com.example.tiendazavaletaapp.gestionAdmid.GestionAdminActivity

class ProductosListaAdminActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        val btnButtonAtras = findViewById<ImageView>(R.id.btnAtras)
        btnButtonAtras.setOnClickListener{
            val intent = Intent(this, GestionAdminActivity::class.java)
            startActivity(intent)
        }
    }
}