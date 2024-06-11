package com.example.tiendazavaletaapp.pedidos

data class Pedidos(
    val CorreoUser:String,
    val EntregaTienda:Boolean,
    val FechaCompra:String,
    val CodPedido:String,
    val Total:Double){
    fun getEntrega(EntregaTienda: Boolean):String{
    if (EntregaTienda == true){
             return "Si"
        } else
             return "No"
    }
}

