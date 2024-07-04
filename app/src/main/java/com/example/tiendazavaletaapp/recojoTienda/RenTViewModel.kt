package com.example.tiendazavaletaapp.recojoTienda

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RenTViewModel: ViewModel()  {
    val mensajeError = MutableLiveData<String>()
    fun validarInfo(nombre: String, dni: String): Boolean{
        return when {
            nombre.isEmpty() -> {
                mensajeError.value = "Ingrese su nombre"
                false
            }
            dni.isEmpty() -> {
                mensajeError.value = "Ingrese su DNI"
                false
            }
            else -> {
                mensajeError.value = ""
                true
            }
        }
    }
}