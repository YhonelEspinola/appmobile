package com.example.tiendazavaletaapp.recuperarContrasenia

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tiendazavaletaapp.R
import com.example.tiendazavaletaapp.login.LoginActivity

class RecuperarContraseniaActivity : AppCompatActivity() {

    private lateinit var viewModel: ReContraViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperar_contrasenia)

        viewModel = ViewModelProvider(this)[ReContraViewModel::class.java]

        val enviarSMS = findViewById<Button>(R.id.btnEnviarSMS)
        val email = findViewById<TextView>(R.id.edtEmailR)

        // Observar los cambios en el LiveData
        viewModel.emailV.observe(this, Observer { isRegistered ->
            if (isRegistered == true) {  // Manejar el resultado de la verificación del correo electrónico
                // Si el correo electrónico está registrado, observar los cambios en resetPassword
                viewModel.resetPassword.observe(this, Observer { isSuccessful ->
                    if (isSuccessful) {
                        Toast.makeText(this, "Correo de restablecimiento enviado", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this,LoginActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "Error al enviar el correo de restablecimiento", Toast.LENGTH_LONG).show()
                    }
                })
            } else if (isRegistered == false) {
                // El correo electrónico no está registrado
                Toast.makeText(this, "Correo electrónico no registrado", Toast.LENGTH_LONG).show()
            }
        })

        enviarSMS.setOnClickListener {
            val emailR = email.text.toString().trim()

            if (emailR.isEmpty()) {
                email.error = "Por favor, ingresa tu correo electrónico"
                email.requestFocus()
                return@setOnClickListener
            }

            // Llamar a la función del ViewModel para enviar el correo de restablecimiento
            viewModel.verificacionEmail(emailR)
        }
     }

}