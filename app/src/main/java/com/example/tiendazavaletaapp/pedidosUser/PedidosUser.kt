package com.example.tiendazavaletaapp.pedidosUser

import com.google.firebase.Timestamp
import com.google.type.DateTime

data class PedidosUser(
    val cantTotalProduct:String,
    val direccion: String,
    val dni:String,
    val email:String,
    val fecha:Timestamp?,
    val fechaestimada:String,
    val nombreApe:String,
    val referencia:String,
    val tipodecompra:String,
    val total:String,
    val ubicacion:String,
    val uid:String,
    val idpedido:String
){
}
