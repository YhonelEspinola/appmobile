package com.example.tiendazavaletaapp.registrarUsuario

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tiendazavaletaapp.Constantes
import com.example.tiendazavaletaapp.databinding.ActivityRegistrarUsuarioBinding
import com.example.tiendazavaletaapp.gestionAdmid.GestionAdminActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegistrarUsuarioActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegistrarUsuarioBinding
    private lateinit var  firebaseAuth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Espere profavor")
        progressDialog.setCanceledOnTouchOutside(false)

        binding.btnRegistrarU.setOnClickListener {
            validarInformacion()
        }
    }

    private var usuario = ""
    private var correo = ""
    private var password = ""
    private var cPassword = ""
    private fun validarInformacion() {
        usuario = binding.textUsuario.text.toString().trim()
        correo = binding.textEmail.text.toString().trim()
        password = binding.textPassword.text.toString().trim()
        cPassword = binding.textCpassword.text.toString().trim()

        if (usuario.isEmpty()){
            binding.textUsuario.error = "Ingrese su Usuario"
            binding.textUsuario.requestFocus()
        }else if (correo.isEmpty()){
            binding.textEmail.error = "Ingrese su Correo"
            binding.textEmail.requestFocus()
        }else if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()){
            binding.textEmail.error = "Correo no valido"
            binding.textEmail.requestFocus()
        }else if (password.isEmpty()){
            binding.textPassword.error = "Ingrese una contraseña"
            binding.textPassword.requestFocus()
        }else if (password.length < 6){
            binding.textPassword.error = "Contraseña muy corta, ingrese 6 o mas caracteres"
            binding.textPassword.requestFocus()
        }else if (cPassword.isEmpty()){
            binding.textCpassword.error = "Repita la Contraseña"
            binding.textCpassword.requestFocus()
        }else if (password != cPassword){
            binding.textCpassword.error = "Contraseña no coincide"
            binding.textCpassword.requestFocus()
        }else{
            registrarUsuario()
        }
    }

    private fun registrarUsuario() {
        progressDialog.setMessage("Creando Cuenta")
        progressDialog.show()

        firebaseAuth.createUserWithEmailAndPassword(correo,password)
            .addOnSuccessListener {
                insertarInfoBD()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this,"Fallo el registro debido a ${e.message}",Toast.LENGTH_SHORT).show()
            }
    }

    private fun insertarInfoBD(){
        progressDialog.setMessage("Guardando informacion...")

        val db = FirebaseFirestore.getInstance()

        val  uidBD = firebaseAuth.uid
        val usuarioBD = usuario
        val correoBD = correo
        val tiempoBD = Constantes().obtenerTiempoD()

        val datosAdministrador = hashMapOf(
        "uid" to  uidBD,
        "usuario" to  usuarioBD,
        "correo" to correoBD,
        "tipoUsuario" to  "administrador",
        "tiempo_registro" to tiempoBD
        )

        db.collection("usuarios")
            .add(datosAdministrador)
            .addOnSuccessListener {
                progressDialog.dismiss()
                startActivity(Intent(this,GestionAdminActivity::class.java))
                finish()
            }
            .addOnFailureListener {e ->
                progressDialog.dismiss()
                Toast.makeText(this,"Fallo el registro en Base de Datos debido a ${e.message}",Toast.LENGTH_SHORT).show()
            }
    }

}