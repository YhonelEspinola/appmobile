package com.example.tiendazavaletaapp.pagoEntrega

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PcEViewModel: ViewModel() {
    val mensajeError = MutableLiveData<String>()

    fun validarInfo(nombre: String, dni: String,ubicacion:String,direccion:String,ref:String): Boolean{
        return when {
            nombre.isEmpty() -> {
                mensajeError.value = "Ingrese su nombre"
                false
            }
            dni.isEmpty() -> {
                mensajeError.value = "Ingrese su DNI"
                false
            }
            ubicacion.isEmpty() -> {
                mensajeError.value = "Ingrese la ubicación"
                false
            }
            direccion.isEmpty() -> {
                mensajeError.value = "Ingrese su dirección"
                false
            }
            ref.isEmpty() -> {
                mensajeError.value = "Ingrese una referencia"
                false
            }
            else -> {
                mensajeError.value = ""
                true
            }
        }
    }
}