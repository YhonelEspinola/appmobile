package com.example.tiendazavaletaapp.login

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.tiendazavaletaapp.databinding.ActivityLoginBinding
import com.example.tiendazavaletaapp.gestionAdmid.GestionAdminActivity
import com.example.tiendazavaletaapp.recuperarContrasenia.RecuperarContraseniaActivity
import com.example.tiendazavaletaapp.registrarUsuario.RegistrarUsuarioActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel

    private lateinit var progressDialog: ProgressDialog
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
            startActivity(Intent(applicationContext, RegistrarUsuarioActivity::class.java))
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
                startActivity(Intent(this,GestionAdminActivity::class.java))
                finishAffinity()
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

}