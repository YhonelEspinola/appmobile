package com.example.tiendazavaletaapp.login

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.tiendazavaletaapp.databinding.ActivityLoginBinding
import com.example.tiendazavaletaapp.gestionAdmid.GestionAdminActivity
import com.example.tiendazavaletaapp.menu.MenuActivity
import com.example.tiendazavaletaapp.recuperarContrasenia.RecuperarContraseniaActivity
import com.example.tiendazavaletaapp.registroCliente.RegistroClienteActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel

    private lateinit var progressDialog: ProgressDialog
    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firestore : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Espere por favor")
        progressDialog.setCanceledOnTouchOutside(false)

        binding.btnLogin.setOnClickListener {
            val email = binding.textEmail.text.toString()
            val password = binding.textPassword.text.toString()

            progressDialog.setMessage("Ingresando")
            progressDialog.show()

            viewModel.validarInfo(email,password)

        }
        binding.textRegistrarse.setOnClickListener {
            startActivity(Intent(applicationContext, RegistroClienteActivity::class.java))
        }
        binding.txtOlvidarContra.setOnClickListener {
            startActivity(Intent(applicationContext, RecuperarContraseniaActivity::class.java))
        }

        observerLiveData()

    }

    private fun observerLiveData(){
        viewModel.userLoginStatus.observe(this){status ->
            if(status){
                progressDialog.dismiss()
                comprobarTipoUsuario()
                Toast.makeText(this,
                    "Bienvenido",
                    Toast.LENGTH_SHORT
                ).show()
            }else{
                progressDialog.dismiss()
                viewModel.masajeError.value?.let {
                    Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.emailError.observe(this){ errorMessage ->
            binding.textEmail.error = errorMessage
            if (errorMessage != null){
                binding.textEmail.requestFocus()
            }
        }

        viewModel.passwordError.observe(this){ errorMessage ->
            binding.textPassword.error = errorMessage
            if (errorMessage != null){
                binding.textPassword.requestFocus()
            }
        }

        viewModel.masajeError.observe(this){ errorMessage ->
            if (errorMessage != null){
                Toast.makeText(this,errorMessage,Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun comprobarTipoUsuario() {
        val userId = firebaseAuth.currentUser?.uid
        if (userId != null) {
            Log.d("LoginActivity", "User ID: $userId")
            firestore.collection("usuarios").document(userId).get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        val role = document.getString("tipoUsuario")
                        Log.d("LoginActivity", "User role: $role")
                        if (role == "administrador") {
                            // Redirigir a la actividad de administrador
                            startActivity(Intent(this, GestionAdminActivity::class.java))
                            finishAffinity()
                        } else {
                            // Redirigir a la actividad de cliente
                            startActivity(Intent(this, MenuActivity::class.java))
                            finish()
                        }
                    } else {
                        Log.d("LoginActivity", "No such document")
                        Toast.makeText(this, "No se encontró información de usuario.", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener { e ->
                    Log.w("LoginActivity", "Error al obtener información de usuario", e)
                    Toast.makeText(this, "Error al obtener información de usuario.", Toast.LENGTH_SHORT).show()
                }
        } else {
            Log.d("LoginActivity", "User ID is null")
            Toast.makeText(this, "Error al obtener información de usuario.", Toast.LENGTH_SHORT).show()
        }
    }

}