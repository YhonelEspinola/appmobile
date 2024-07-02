package com.example.tiendazavaletaapp.carrito

data class Carrito(
    var precioTotal: Double = 0.0,
    var cantidad: Int = 0,
    val nombre: String = "",
    val marca: String = "",
    val precio: Double = 0.0,
    val imgProducto: String = "",
    val userId: String = "",
    val codProducto: String = ""
)
