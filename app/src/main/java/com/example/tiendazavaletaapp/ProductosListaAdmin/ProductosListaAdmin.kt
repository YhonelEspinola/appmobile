package com.example.tiendazavaletaapp.ProductosListaAdmin

data class ProductosListaAdmin (
    val  nProducto:String,
    val  marca:String,
    val  categoria:String,
    val codProducto: String,
    val  precio: Double,
    val imgProducto:String,
    val stock: Int,
    val descripcion: String
)