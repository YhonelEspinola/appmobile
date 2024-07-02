package com.example.tiendazavaletaapp.Perfil

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class PerfilViewModel : ViewModel() {

    private val _nombreU = MutableLiveData<String?>() // Variable mutableLiveData privada que contendra el nombre del usuario
    private val _correoU = MutableLiveData<String?>() // Variable mutableLiveData privada que contendra el correo del usuario

    // Variable liveData pública para que otras clases puedan observar los cambios en el nombre del usuario
    val nombreU: LiveData<String?> get() = _nombreU
    // Variable liveData pública para que otras clases puedan observar los cambios en el correo del usuario
    val correoU: LiveData<String?> get() = _correoU

    fun leerInformacion() {
        val db = FirebaseFirestore.getInstance().collection("usuarios") // Obtiene la colección "usuarios" en Firestore
        val userId = FirebaseAuth.getInstance().uid ?: return // Obtiene el ID del usuario actual

        db.document(userId).get() // Obtenie el documento del usuario usando el ID del usuario
            .addOnSuccessListener { document ->
                val nombre = document.getString("usuario") //Extrae el nombre del documento
                val correo = document.getString("correo") //Extrae el correo del documento
                _nombreU.value = nombre // Actualiza el valor de la variable mutableLiveData con el nombre del usuario
                _correoU.value = correo // Actualiza el valor de la variable mutableLiveData con el correo del usuario
            }
    }
}