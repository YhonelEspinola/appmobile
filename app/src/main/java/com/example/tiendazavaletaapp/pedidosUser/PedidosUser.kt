package com.example.tiendazavaletaapp.pedidosUser

data class PedidosUser(val EntregaTienda:Boolean,
                       val Direccion: String,
                       val FechaCompra:String,
                       val CodPedido:String,
                       val Total:Double ){

    fun getEntrega(EntregaTienda: Boolean):String{
        if (EntregaTienda == true){
            return "Recojo en tienda"
        } else
            return "Pago contra entrega"
    }
}
