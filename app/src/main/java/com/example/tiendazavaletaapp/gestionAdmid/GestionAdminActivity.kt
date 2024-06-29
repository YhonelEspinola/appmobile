package com.example.tiendazavaletaapp.gestionAdmid

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.tiendazavaletaapp.ProductosListaAdmin.ProductosListaAdminActivity
import com.example.tiendazavaletaapp.R
import com.example.tiendazavaletaapp.createProductos.CreateProductosActivity
import com.example.tiendazavaletaapp.login.LoginActivity
import com.example.tiendazavaletaapp.menu.MenuActivity
import com.example.tiendazavaletaapp.pedidos.PedidosActivity
import com.example.tiendazavaletaapp.registrarUsuario.RegistrarUsuarioActivity
import com.google.firebase.auth.FirebaseAuth

class GestionAdminActivity:AppCompatActivity() {

    private var  firebaseAuth: FirebaseAuth?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestion_admid)

        firebaseAuth = FirebaseAuth.getInstance()

        val btnCrearProductos = findViewById<Button>(R.id.btnButtonCrearProductos)
        val btnVerProductos = findViewById<Button>(R.id.btnButtonVerProductos)
        val btnVerPedidos = findViewById<Button>(R.id.btnButtonVerPedios)
        val btnNewAdmin = findViewById<Button>(R.id.btnButtonAdmin)
        val btnCerrarSesion = findViewById<Button>(R.id.btnButonCerrarSesion)

        btnCrearProductos.setOnClickListener{
            val intent = Intent(this, CreateProductosActivity::class.java)
            startActivity(intent)
        }

        btnVerProductos.setOnClickListener{
            val intent = Intent(this, ProductosListaAdminActivity::class.java)
            startActivity(intent)
        }
        btnVerPedidos.setOnClickListener{
            val intent = Intent(this , PedidosActivity::class.java )
            startActivity(intent)
        }

        btnNewAdmin.setOnClickListener {
            startActivity(Intent(this,RegistrarUsuarioActivity::class.java))
        }

        btnCerrarSesion.setOnClickListener{
            cerrarSesion()
        }

    }

    private fun cerrarSesion(){
        firebaseAuth!!.signOut()
        startActivity(Intent(this, MenuActivity::class.java))
        finishAffinity()
    }

}