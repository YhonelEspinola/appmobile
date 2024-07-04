package com.example.tiendazavaletaapp.detallePedidoPce

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tiendazavaletaapp.carrito.Carrito
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class DetallePedidoViewModel: ViewModel()  {
    private  val db= FirebaseFirestore.getInstance()
    private val colleccion= db.collection("carrito")
    val listPc = MutableLiveData<List<Carrito>>()
    val totalPrecio = MutableLiveData<Double>()
    val totalCantidad = MutableLiveData<Int>()

    fun listPce(userId:String){
        colleccion
            .whereEqualTo("userId", userId)
            .get()
            .addOnSuccessListener { querySnapshot ->
                var newList = arrayListOf<Carrito>()
                var total = 0.0
                var cantidad = 0
                for(document in querySnapshot){
                    val data = document.data
                    val nombre = data["nombre"] as? String ?: ""
                    val marca = data["marca"] as? String ?: ""
                    val codProducto = data["codProducto"] as? String ?: ""
                    val imagen = data["imgProducto"] as? String ?: ""
                    val userId = data["userId"] as? String ?: ""
                    val precio = data["precio"] as? Double ?: 0.0
                    val precioTotal = data["precioTotal"] as? Double ?: 0.0
                    val cantidadP = (data["cantidad"] as? Long)?.toInt() ?: 0
                    val modelo= Carrito(precioTotal,cantidadP,nombre,marca,precio,imagen,userId,codProducto)
                    newList.add((modelo))
                    total += precioTotal
                    cantidad += cantidadP
                }
                listPc.value=newList
                totalPrecio.value = total
                totalCantidad.value = cantidad
            }
    }

    fun guardarPedido(
        cantTotalProduct:String,
        direccion:String,
        dni:String,
        nombreApe:String,
        referencia:String,
        tipodecompra:String,
        total:String,
        ubicacion:String,
        uid:String,
        email:String,
        fechaestimada:String){

        val data = hashMapOf(
            "cantTotalProduct" to cantTotalProduct,
            "direccion" to direccion,
            "dni" to dni,
            "nombreApe" to nombreApe,
            "referencia" to referencia,
            "tipodecompra" to tipodecompra,
            "total" to total,
            "ubicacion" to ubicacion,
            "uid" to uid,
            "email" to email,
            "fecha" to Timestamp.now(),
            "fechaestimada" to fechaestimada
        )

        db.collection("pedido")
            .add(data)
            .addOnSuccessListener { documentReference ->
                val pedidoId = documentReference.id
                guardarDetallePedido(pedidoId,uid)
            }
    }

    private fun guardarDetallePedido(pedidoId: String,userId:String) {
        val productos = listPc.value ?: return
        val batch = db.batch()
        if (productos.isEmpty()) {
            Log.d("GuardarDetallePedido", "No hay productos para guardar.")
            return
        }
        for (producto in productos) {
            val detallePedidoRef = db.collection("detallepedido").document()
            val detallePedidoData = hashMapOf(
                "pedidoId" to pedidoId,
                "codProducto" to producto.codProducto,
                "nProducto" to producto.nombre,
                "cantidad" to producto.cantidad,
                "precio" to producto.precio,
            )
            batch.set(detallePedidoRef, detallePedidoData)
            val productoRef = db.collection("producto").document(producto.codProducto)
            batch.update(productoRef, "stock", FieldValue.increment(-producto.cantidad.toLong()))
        }
        val carritoRef = db.collection("carrito")
            .whereEqualTo("userId", userId)

        carritoRef.get().addOnSuccessListener { documents ->
            for (document in documents) {
                batch.delete(document.reference)
            }
            batch.commit().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("GuardarDetallePedido", "Productos guardados correctamente y eliminados del carrito.")
                } else {
                    Log.e("GuardarDetallePedido", "Error al guardar productos o eliminar del carrito: ", task.exception)
                }
            }
        }.addOnFailureListener { e ->
            Log.e("GuardarDetallePedido", "Error al obtener documentos del carrito: ", e)
        }
    }
}