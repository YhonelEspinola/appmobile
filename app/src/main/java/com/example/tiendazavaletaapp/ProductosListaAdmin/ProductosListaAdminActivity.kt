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
import com.google.firebase.firestore.FirebaseFirestore

class ProductosListaAdminActivity: AppCompatActivity() {
    private  val db=FirebaseFirestore.getInstance()
    private val colleccion= db.collection("producto")
    private lateinit var adapterPLA: ProductosListaAdminAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productos_listadmin)

        val recyclerProductosListAdmin =findViewById<RecyclerView>(R.id.recyclerProductosListAdmin)
        adapterPLA = ProductosListaAdminAdapter(emptyList())
        recyclerProductosListAdmin.adapter=adapterPLA
        recyclerProductosListAdmin.layoutManager= LinearLayoutManager(this)

        val btnButtonAtras = findViewById<ImageView>(R.id.btnAtras)
        btnButtonAtras.setOnClickListener{
            val intent = Intent(this, GestionAdminActivity::class.java)
            startActivity(intent)
        }
        listProductos()
    }

    private fun listProductos(){
        colleccion.get()
            .addOnSuccessListener { querySnapshot ->
                val listProductosListaAdmin = mutableListOf<ProductosListaAdmin>()
                for(document in querySnapshot){
                    val nProducto = document.getString("nProducto") ?: ""
                    val marca = document.getString("marca") ?: ""
                    val categoria = document.getString("categoria") ?: ""
                    val codigo = document.id
                    val precio = document.getDouble("precio") ?: 0.0
                    val imgProducto = document.getString("imgProducto") ?: ""
                    val stock = document.getLong("stock")?.toInt() ?: 0
                    val descripcion = document.getString("descripcion") ?: ""
                    if(document != null){
                        val modelo=ProductosListaAdmin(nProducto,marca,categoria,codigo,precio,imgProducto,stock,descripcion)
                        listProductosListaAdmin.add((modelo))
                    }
                }
                adapterPLA.setDatos(listProductosListaAdmin)
            }
    }
}