package com.example.tiendazavaletaapp.registrarUsuario

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.tiendazavaletaapp.Constantes
import com.example.tiendazavaletaapp.databinding.ActivityRegistrarUsuarioBinding
import com.example.tiendazavaletaapp.gestionAdmid.GestionAdminActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegistrarUsuarioActivity : AppCompatActivity() {

    private lateinit var viewModel: RegistroViewModel

    private lateinit var binding : ActivityRegistrarUsuarioBinding
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[RegistroViewModel::class.java]

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Espere profavor")
        progressDialog.setCanceledOnTouchOutside(false)

        binding.btnRegistrarU.setOnClickListener {

            val usuario = binding.textUsuario.text.toString().trim()
            val correo = binding.textEmail.text.toString().trim()
            val password = binding.textPassword.text.toString().trim()
            val cPassword = binding.textCpassword.text.toString().trim()

            progressDialog.setMessage("Registrando Informacion...")
            progressDialog.show()

            viewModel.validarInformacion(usuario,correo,password,cPassword)
        }

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.userRegisterStatus.observe(this) { status ->
            if (status) {
                progressDialog.dismiss()
                startActivity(Intent(this, GestionAdminActivity::class.java))
                finish()
            }else{
                progressDialog.dismiss()
            }
        }

        viewModel.usuarioError.observe(this) { errorMessage ->
            binding.textUsuario.error = errorMessage
        }

        viewModel.correoError.observe(this) { errorMessage ->
            binding.textEmail.error = errorMessage
        }

        viewModel.passwordError.observe(this) { errorMessage ->
            binding.textPassword.error = errorMessage
        }

        viewModel.cPasswordError.observe(this) { errorMessage ->
            binding.textCpassword.error = errorMessage
        }

        viewModel.mensajeError.observe(this) { errorMessage ->
            if (errorMessage != null) {
                progressDialog.dismiss()
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

}