package com.example.tiendazavaletaapp.recuperarContrasenia

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ReContraViewModel: ViewModel() {

    // Variables liveData para indicar si el email está registrado en Firestore
    private val _email = MutableLiveData<Boolean?>()
    val emailV: LiveData<Boolean?> get() = _email

    // Variables liveData para indicar si el correo de restablecimiento de contraseña fue enviado exitosamente
    private val _resetPassword = MutableLiveData<Boolean>()
    val resetPassword: LiveData<Boolean> get() = _resetPassword

    // Función para verificar si el email está registrado en la colección "usuarios"
    fun verificacionEmail(email: String) {
        val db = FirebaseFirestore.getInstance().collection("usuarios")
        // Realiza una consulta a Firestore para verificar si el email existe en la colección
        db.whereEqualTo("correo", email).get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val documents = task.result
                    if (documents != null && !documents.isEmpty) {
                        // El correo electrónico está registrado
                        _email.value = true
                        sendPasswordResetEmail(email) // Enviar el correo de restablecimiento de contraseña
                    } else {
                        // El correo electrónico no está registrado
                        _email.value = false
                    }
                } else {
                    // Error al verificar el correo electrónico
                    _email.value = false
                }
            }
    }

    // Función para enviar el correo de restablecimiento de contraseña
    private fun sendPasswordResetEmail(emailAddress: String) {
        FirebaseAuth.getInstance().sendPasswordResetEmail(emailAddress)
            .addOnCompleteListener { task ->
                _resetPassword.value = task.isSuccessful
            }
    }
}