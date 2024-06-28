package com.example.tiendazavaletaapp.registroCliente

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.tiendazavaletaapp.databinding.ActivityRegistroClienteBinding
import com.example.tiendazavaletaapp.menu.MenuActivity

class RegistroClienteActivity : AppCompatActivity() {

    private lateinit var viewModel: RegitroClienteViewModel

    private lateinit var binding: ActivityRegistroClienteBinding
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroClienteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[RegitroClienteViewModel::class.java]

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Espere profavor")
        progressDialog.setCanceledOnTouchOutside(false)

        binding.btnRegistrarC.setOnClickListener {

            val usuarioC = binding.textUsuarioC.text.toString().trim()
            val correoC = binding.textEmailC.text.toString().trim()
            val passwordC = binding.textPasswordC.text.toString().trim()
            val cPasswordC = binding.textCpasswordC.text.toString().trim()

            progressDialog.setMessage("Registrando Informacion...")
            progressDialog.show()

            viewModel.validarInformacion(usuarioC,correoC,passwordC,cPasswordC)
        }

        observeLiveData()

    }

    private fun observeLiveData() {
        viewModel.userRegisterCStatus.observe(this) { status ->
            if (status) {
                progressDialog.dismiss()
                startActivity(Intent(this, MenuActivity::class.java))
                finish()
            }else{
                progressDialog.dismiss()
            }
        }

        viewModel.usuarioCError.observe(this) { errorMessage ->
            binding.textUsuarioC.error = errorMessage
        }

        viewModel.correoCError.observe(this) { errorMessage ->
            binding.textEmailC.error = errorMessage
        }

        viewModel.passwordCError.observe(this) { errorMessage ->
            binding.textPasswordC.error = errorMessage
        }

        viewModel.cPasswordCError.observe(this) { errorMessage ->
            binding.textCpasswordC.error = errorMessage
        }

        viewModel.mensajeError.observe(this) { errorMessage ->
            if (errorMessage != null) {
                progressDialog.dismiss()
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}