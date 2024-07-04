package com.example.tiendazavaletaapp.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tiendazavaletaapp.ProductosListaAdmin.ProductosListaAdmin
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class ListCategoriaViewModel: ViewModel(){
    private  val db= FirebaseFirestore.getInstance()
    private val colleccion= db.collection("producto")
    val listProductosLCat = MutableLiveData<List<ProductosListaAdmin>>()
    val listProductosMV = MutableLiveData<List<ProductosListaAdmin>>()
    val listProductosN = MutableLiveData<List<ProductosListaAdmin>>()

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
                    val fecha = data["fecha"] as? Timestamp

                        val modelo= ProductosListaAdmin(nProducto,marca,categoria,codigo,precio,imgProducto,stock,descripcion,fecha)
                        newList.add((modelo))

                }
                listProductosLCat.value=newList
            }
    }

    fun listProductosHomeI(){

        db.collection("detallepedido")
            .get()
            .addOnSuccessListener { querySnapshot ->
                val productQuantityMap = mutableMapOf<String, Int>()

                for (document in querySnapshot) {
                    val codProducto = document.getString("codProducto") ?: continue
                    val cantidad = (document.getLong("cantidad")?.toInt()) ?: continue

                    if (productQuantityMap.containsKey(codProducto)) {
                        productQuantityMap[codProducto] = productQuantityMap[codProducto]!! + cantidad
                    } else {
                        productQuantityMap[codProducto] = cantidad
                    }
                }

                // Ordena los productos por cantidad total pedida de mayor a menor
                val sortedProducts = productQuantityMap.toList().sortedByDescending { (_, cantidad) -> cantidad }.take(4).toMap()

                // Ahora obtén los detalles del producto desde la colección "producto"
                getProductosDetalles(sortedProducts)
            }
    }
    fun getProductosDetalles(sortedProducts: Map<String, Int>) {
        val productosList = mutableListOf<ProductosListaAdmin>()

        sortedProducts.forEach { (codigo) ->
            db.collection("producto").document(codigo)
                .get()
                .addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.exists()) {
                        val data = documentSnapshot.data ?: return@addOnSuccessListener
                        val nProducto = data["nProducto"] as? String ?: ""
                        val marca = data["marca"] as? String ?: ""
                        val categoria = data["categoria"] as? String ?: ""
                        val precio = data["precio"] as? Double ?: 0.0
                        val imgProducto = data["imgProducto"] as? String ?: ""
                        val stock = (data["stock"] as? Long)?.toInt() ?: 0
                        val descripcion = data["descripcion"] as? String ?: ""
                        val fecha = data["fecha"] as? Timestamp

                        val modelo = ProductosListaAdmin(nProducto, marca, categoria, codigo, precio, imgProducto, stock, descripcion, fecha)
                        productosList.add(modelo)

                        // Verifica si todos los productos han sido añadidos
                        if (productosList.size == sortedProducts.size) {
                            listProductosMV.value = productosList
                        }
                    }
                }
        }
    }
    fun listProductosHomeII(){

        colleccion
            .orderBy("fecha", Query.Direction.DESCENDING)
            .limit(4)
            .get()
            .addOnSuccessListener { querySnapshot ->
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
                val fecha = data["fecha"] as? Timestamp

                val modelo= ProductosListaAdmin(nProducto,marca,categoria,codigo,precio,imgProducto,stock,descripcion,fecha)
                newList.add((modelo))

            }
                listProductosN.value=newList
        }
    }

    fun listProductosNuevos(){
        colleccion
            .orderBy("fecha", Query.Direction.DESCENDING)
            .limit(10)
            .get()
            .addOnSuccessListener { querySnapshot ->
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
                    val fecha = data["fecha"] as? Timestamp

                    val modelo= ProductosListaAdmin(nProducto,marca,categoria,codigo,precio,imgProducto,stock,descripcion,fecha)
                    newList.add((modelo))

                }
                listProductosN.value=newList
            }
    }

}