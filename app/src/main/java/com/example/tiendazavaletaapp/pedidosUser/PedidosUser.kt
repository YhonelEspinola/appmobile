package com.example.tiendazavaletaapp.pedidosUser

data class PedidosUser(val EntregaTienda:Boolean,
                       val FechaCompra:String,
                       val CodPedido:String,
                       val Total:Double ){

    fun getEntrega(EntregaTienda: Boolean):String{
        if (EntregaTienda == true){
            return "Si"
        } else
            return "No"
    }
}
