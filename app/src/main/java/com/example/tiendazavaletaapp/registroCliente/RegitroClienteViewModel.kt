package com.example.tiendazavaletaapp.registroCliente

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tiendazavaletaapp.Constantes
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegitroClienteViewModel : ViewModel() {

    private  lateinit var  firebaseAuth: FirebaseAuth

    val userRegisterCStatus = MutableLiveData<Boolean>()
    val usuarioCError = MutableLiveData<String>()
    val correoCError = MutableLiveData<String>()
    val passwordCError = MutableLiveData<String>()
    val cPasswordCError = MutableLiveData<String>()
    val mensajeError = MutableLiveData<String>()

    fun validarInformacion(usuarioC:String,correoC: String,passwordC: String, cPasswordC: String) {
        if (usuarioC.isEmpty()){
            usuarioCError.value = "Ingrese un Usuario"
            userRegisterCStatus.value = false
        }else if (correoC.isEmpty()){
            correoCError.value = "Ingrese su Correo"
            userRegisterCStatus.value = false
        }else if (!Patterns.EMAIL_ADDRESS.matcher(correoC).matches()){
            correoCError.value = "Correo no valido"
            userRegisterCStatus.value = false
        }else if (passwordC.isEmpty()){
            passwordCError.value = "Ingrese una contraseña"
            userRegisterCStatus.value = false
        }else if (passwordC.length < 6){
            passwordCError.value = "Contraseña muy corta, ingrese 6 o mas caracteres"
            userRegisterCStatus.value = false
        }else if (cPasswordC.isEmpty()){
            cPasswordCError.value = "Repita la Contraseña"
            userRegisterCStatus.value = false
        }else if (passwordC != cPasswordC){
            cPasswordCError.value = "Contraseña no coincide"
            userRegisterCStatus.value = false
        }else{
            registrarUsuario(usuarioC,correoC,passwordC)
        }
    }

    private fun registrarUsuario(usuarioC: String,correoC: String, passwordC: String) {
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.createUserWithEmailAndPassword(correoC,passwordC)
            .addOnCompleteListener {task ->
                if (task.isSuccessful){
                    insertarInfoBD(usuarioC,correoC)
                }else{
                    userRegisterCStatus.value = false
                    mensajeError.value = "Error al registrar: ${task.exception?.message}"
                }
            }
    }

    private fun insertarInfoBD(usuarioC: String, correoC: String){
        val db = FirebaseFirestore.getInstance()

        val  uidBD = firebaseAuth.uid
        val tiempoBD = Constantes().obtenerTiempoD()

        val datosAdministrador = hashMapOf(
            "uid" to  uidBD,
            "usuario" to  usuarioC,
            "correo" to correoC,
            "tipoUsuario" to  "cliente",
            "tiempo_registro" to tiempoBD
        )

        db.collection("usuarios")
            .add(datosAdministrador)
            .addOnSuccessListener {
                userRegisterCStatus.value = true
            }
            .addOnFailureListener {e ->
                userRegisterCStatus.value = false
                mensajeError.value ="Fallo el registro en la Base de Datos debido a ${e.message}"
            }
    }

}