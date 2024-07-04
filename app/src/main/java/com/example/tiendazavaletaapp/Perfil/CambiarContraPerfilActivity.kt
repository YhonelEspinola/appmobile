package com.example.tiendazavaletaapp.Perfil

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tiendazavaletaapp.R
import com.example.tiendazavaletaapp.recuperarContrasenia.RecuperarContraseniaActivity

class CambiarContraPerfilActivity:AppCompatActivity() {

    private lateinit var viewModel: PerfilViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cambiar_contra_perfil)

        viewModel = ViewModelProvider(this)[PerfilViewModel::class.java]

        val newPassword: EditText = findViewById(R.id.new_password)
        val currentPassword: EditText = findViewById(R.id.current_password)
        val btnGuardar: Button = findViewById(R.id.btnGuardar)
        val equisImageView = findViewById<ImageView>(R.id.equis)
        val btncancelar = findViewById<Button>(R.id.btnCancelar)
        val forgoOContra = findViewById<TextView>(R.id.forgot_password)

        btnGuardar.setOnClickListener {
            val nuevaContrasena = newPassword.text.toString().trim()
            val contrasenaActual = currentPassword.text.toString().trim()

            if (contrasenaActual.isEmpty()) {
                currentPassword.error = "Por favor, ingresa tu contraseña actual"
                currentPassword.requestFocus()
                return@setOnClickListener
            }

            if (nuevaContrasena.isEmpty()) {
                newPassword.error = "Por favor, ingresa una nueva contraseña"
                newPassword.requestFocus()
                return@setOnClickListener
            }

            viewModel.actualizarContrasena(contrasenaActual, nuevaContrasena)
        }

        viewModel.newpass.observe(this, Observer { exitosa ->
            if (exitosa == true) {
                Toast.makeText(this, "Contraseña actualizada exitosamente", Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(this, "Error, verifique que los campos esten correctos", Toast.LENGTH_LONG).show()
            }
        })

        forgoOContra.setOnClickListener {
            startActivity(Intent(this,RecuperarContraseniaActivity::class.java))
        }

        equisImageView.setOnClickListener {
            finish()
        }
        btncancelar.setOnClickListener {
            finish()
        }

    }
}