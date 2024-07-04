package com.example.tiendazavaletaapp.Perfil

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class PerfilViewModel : ViewModel() {

    private val _nombreU = MutableLiveData<String?>() // Variable mutableLiveData privada que contendra el nombre del usuario
    private val _correoU = MutableLiveData<String?>() // Variable mutableLiveData privada que contendra el correo del usuario
    private val _newpass = MutableLiveData<Boolean>()

    // Variable liveData pública para que otras clases puedan observar los cambios en el nombre del usuario
    val nombreU: LiveData<String?> get() = _nombreU
    // Variable liveData pública para que otras clases puedan observar los cambios en el correo del usuario
    val correoU: LiveData<String?> get() = _correoU
    val newpass: LiveData<Boolean> get() = _newpass

    val db = FirebaseFirestore.getInstance().collection("usuarios") // Obtiene la colección "usuarios" en Firestore

    fun leerInformacion() {

        val userId = FirebaseAuth.getInstance().uid ?: return // Obtiene el ID del usuario actual

        db.document(userId).get() // Obtenie el documento del usuario usando el ID del usuario
            .addOnSuccessListener { document ->
                val nombre = document.getString("usuario") //Extrae el nombre del documento
                val correo = document.getString("correo") //Extrae el correo del documento
                _nombreU.value = nombre // Actualiza el valor de la variable mutableLiveData con el nombre del usuario
                _correoU.value = correo // Actualiza el valor de la variable mutableLiveData con el correo del usuario
            }
    }

    fun actualizarInformacion(nombre: String) {
        val userId = FirebaseAuth.getInstance().uid ?: return

        val userUpdates = hashMapOf<String, Any>(
            "usuario" to nombre
        )

        db.document(userId).update(userUpdates)
            .addOnSuccessListener {
                _nombreU.value = nombre
            }
            .addOnFailureListener { exception ->
            }
    }

    fun actualizarContrasena(currentContraseña: String, newContraseña: String) {
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null && user.email != null) {
            // Credenciales para re-autenticar al usuario
            val credential = EmailAuthProvider.getCredential(user.email!!, currentContraseña)

            // Re-autenticar al usuario
            user.reauthenticate(credential)
                .addOnCompleteListener { reauthTask ->
                    if (reauthTask.isSuccessful) {
                        // Actualizar la contraseña
                        user.updatePassword(newContraseña)
                            .addOnCompleteListener { updateTask ->
                                _newpass.value = updateTask.isSuccessful
                            }
                    } else {
                        _newpass.value = false
                    }
                }
        } else {
            _newpass.value = false
        }
    }
}