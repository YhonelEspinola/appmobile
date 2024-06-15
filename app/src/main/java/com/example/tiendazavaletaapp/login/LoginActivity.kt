package com.example.tiendazavaletaapp.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tiendazavaletaapp.R
import com.example.tiendazavaletaapp.gestionAdmid.GestionAdminActivity
import com.example.tiendazavaletaapp.menu.MenuActivity
import com.example.tiendazavaletaapp.recuperarContrasenia.RecuperarContraseniaActivity
import com.example.tiendazavaletaapp.registrarUsuario.RegistrarUsuarioActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val email = findViewById<EditText>(R.id.edtEmail)
        val password = findViewById<EditText>(R.id.edtPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnRegister = findViewById<TextView>(R.id.textRegistrarse)
        val txtOlvidarContra = findViewById<TextView>(R.id.txtOlvidarContra)

        btnLogin.setOnClickListener {
            val emailVale = email.text.toString()
            val passwordValue = password.text.toString()

            if (emailVale.equals("admin@gmail.com") && passwordValue.equals("123456")) {
                val intent = Intent(this, GestionAdminActivity::class.java)
                startActivity(intent)
            }
            else if(emailVale.equals("jorge@gmail.com") && passwordValue.equals("123")) {
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
            }
            else {
                Toast.makeText(this,
                    "Verifique datos", Toast.LENGTH_SHORT).show()
            }
        }
        btnRegister.setOnClickListener {
            val intent = Intent(this, RegistrarUsuarioActivity::class.java)
            startActivity(intent)
        }
        txtOlvidarContra.setOnClickListener {
            val intent = Intent(this, RecuperarContraseniaActivity::class.java)
            startActivity(intent)
        }
    }
}