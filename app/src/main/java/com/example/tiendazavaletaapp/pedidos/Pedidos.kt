package com.example.tiendazavaletaapp.pedidos

data class Pedidos(
    val CorreoUser:String,
    val EntregaTienda:Boolean,
    val FechaCompra:String,
    val CodPedido:String,
    val Total:Double
)
