package com.example.tiendazavaletaapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tiendazavaletaapp.gestionAdmid.GestionAdminActivity
import com.example.tiendazavaletaapp.login.LoginActivity
import com.example.tiendazavaletaapp.menu.MenuActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        firebaseAuth = FirebaseAuth.getInstance()

        val handler = Handler(Looper.getMainLooper())

        handler.postDelayed({
            comprobarTipoUsuario()
        }, 1500)



    }

    private fun comprobarTipoUsuario() {
        val userId = firebaseAuth.currentUser?.uid
        if (userId == null) {
            startActivity(Intent(this,MenuActivity::class.java))
            finish()
            Toast.makeText(this,"Bienvenido a Comercial Zavaleta", Toast.LENGTH_SHORT).show()
        } else {
            FirebaseFirestore.getInstance().collection("usuarios").document(userId).get()
                .addOnSuccessListener {
                    val role = it.getString("tipoUsuario")
                    if (role == "administrador") {
                        // Redirigir a la actividad de administrador
                        startActivity(Intent(this, GestionAdminActivity::class.java))
                        finishAffinity()
                    } else {
                        // Redirigir a la actividad de cliente
                        startActivity(Intent(this, MenuActivity::class.java))
                        finish()
                    }
                }
        }
    }


    //    fun verBienvenida(){
//        object:CountDownTimer(2000,1000){
//            override fun onTick(millisUntilFinished: Long) {
//            }
//
//            override fun onFinish() {
//                val intent=Intent(this@MainActivity, LoginActivity::class.java)
//                startActivity(intent)
//                finishAffinity()
//            }
//
//        }.start()
//    }

}

