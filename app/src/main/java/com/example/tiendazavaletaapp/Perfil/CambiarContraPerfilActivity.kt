package com.example.tiendazavaletaapp.Perfil

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.tiendazavaletaapp.R

class CambiarContraPerfilActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cambiar_contra_perfil)
        val equisImageView = findViewById<ImageView>(R.id.equis)
        val btncancelar = findViewById<Button>(R.id.btnCancelar)
        equisImageView.setOnClickListener {
            finish()
        }
        btncancelar.setOnClickListener {
            finish()
        }

    }
}