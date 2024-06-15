package com.example.tiendazavaletaapp.editProductos

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.tiendazavaletaapp.ProductosListaAdmin.ProductosListaAdminActivity
import com.example.tiendazavaletaapp.R
import com.example.tiendazavaletaapp.gestionAdmid.GestionAdminActivity

class EditProductosActivity :AppCompatActivity(){

    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()){ uri->
        if(uri != null){
            imgProducto.setImageURI(uri)
        }else{
            Log.i("ari","img no seleccionada")
        }
    }

    lateinit var btnImagen: Button
    lateinit var imgProducto: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_producto)

        btnImagen = findViewById(R.id.btnImagen)
        imgProducto = findViewById(R.id.imgProducto)
        btnImagen.setOnClickListener{
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
        val btnButtonAtras = findViewById<ImageView>(R.id.btnAtras)
        btnButtonAtras.setOnClickListener{
            val intent = Intent(this, ProductosListaAdminActivity::class.java)
            startActivity(intent)
        }
    }
}