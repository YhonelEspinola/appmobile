package com.example.tiendazavaletaapp.ProductosListaAdmin

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.R
import com.example.tiendazavaletaapp.gestionAdmid.GestionAdminActivity
import com.google.firebase.Timestamp
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

        val searchView = findViewById<SearchView>(R.id.search)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { adapterPLA.filter(it) }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { adapterPLA.filter(it) }
                return false
            }
        })
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
                    val fecha = document.getTimestamp("fecha")
                    if(document != null){
                        val modelo=ProductosListaAdmin(nProducto,marca,categoria,codigo,precio,imgProducto,stock,descripcion,fecha)
                        listProductosListaAdmin.add((modelo))
                    }
                }
                adapterPLA.setDatos(listProductosListaAdmin)
            }
    }
}