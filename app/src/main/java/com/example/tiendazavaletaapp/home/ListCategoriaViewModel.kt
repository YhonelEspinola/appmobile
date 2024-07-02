package com.example.tiendazavaletaapp.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tiendazavaletaapp.ProductosListaAdmin.ProductosListaAdmin
import com.google.firebase.firestore.FirebaseFirestore

class ListCategoriaViewModel: ViewModel(){
    private  val db= FirebaseFirestore.getInstance()
    private val colleccion= db.collection("producto")
    val listProductosLCat = MutableLiveData<List<ProductosListaAdmin>>()
    val listProductosMV = MutableLiveData<List<ProductosListaAdmin>>()
    val listProductosR = MutableLiveData<List<ProductosListaAdmin>>()

    fun listProductos(catego: String){
        val query = if (catego.isNullOrEmpty()){
            colleccion.get()} else {colleccion.whereEqualTo("categoria", catego).get()}

            query.addOnSuccessListener { querySnapshot ->
                var newList = arrayListOf<ProductosListaAdmin>()
                for(document in querySnapshot){
                    val data = document.data
                    val nProducto = data["nProducto"] as? String ?: ""
                    val marca = data["marca"] as? String ?: ""
                    val categoria = data["categoria"] as? String ?: ""
                    val codigo = document.id
                    val precio = data["precio"] as? Double ?: 0.0
                    val imgProducto = data["imgProducto"] as? String ?: ""
                    val stock = (data["stock"] as? Long)?.toInt() ?: 0
                    val descripcion = data["descripcion"] as? String ?: ""

                        val modelo= ProductosListaAdmin(nProducto,marca,categoria,codigo,precio,imgProducto,stock,descripcion)
                        newList.add((modelo))

                }
                listProductosLCat.value=newList
            }
    }

    fun listProductosHomeI(filtro: String){
        val query = if (filtro.isNullOrEmpty())
            colleccion.get()
        else
            colleccion.whereEqualTo("categoria", filtro).get()

        query.addOnSuccessListener { querySnapshot ->
            var newList = arrayListOf<ProductosListaAdmin>()
            for(document in querySnapshot){
                val data = document.data
                val nProducto = data["nProducto"] as? String ?: ""
                val marca = data["marca"] as? String ?: ""
                val categoria = data["categoria"] as? String ?: ""
                val codigo = document.id
                val precio = data["precio"] as? Double ?: 0.0
                val imgProducto = data["imgProducto"] as? String ?: ""
                val stock = (data["stock"] as? Long)?.toInt() ?: 0
                val descripcion = data["descripcion"] as? String ?: ""

                val modelo= ProductosListaAdmin(nProducto,marca,categoria,codigo,precio,imgProducto,stock,descripcion)
                newList.add((modelo))

            }
            listProductosMV.value=newList
        }
    }

    fun listProductosHomeII(filtro: String){
        val query = if (filtro.isNullOrEmpty())
            colleccion.get()
        else
            colleccion.whereEqualTo("categoria", filtro).get()

        query.addOnSuccessListener { querySnapshot ->
            var newList = arrayListOf<ProductosListaAdmin>()
            for(document in querySnapshot){
                val data = document.data
                val nProducto = data["nProducto"] as? String ?: ""
                val marca = data["marca"] as? String ?: ""
                val categoria = data["categoria"] as? String ?: ""
                val codigo = document.id
                val precio = data["precio"] as? Double ?: 0.0
                val imgProducto = data["imgProducto"] as? String ?: ""
                val stock = (data["stock"] as? Long)?.toInt() ?: 0
                val descripcion = data["descripcion"] as? String ?: ""

                val modelo= ProductosListaAdmin(nProducto,marca,categoria,codigo,precio,imgProducto,stock,descripcion)
                newList.add((modelo))

            }
            listProductosR.value=newList
        }
    }
}