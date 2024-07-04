package com.example.tiendazavaletaapp.detallePedidoUser

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tiendazavaletaapp.pedidosUser.PedidosUser
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore

class DetallePedidoUserViewModel: ViewModel()  {
    private  val db= FirebaseFirestore.getInstance()
    val listDetallePedidos = MutableLiveData<List<DetallePedidoUser>>()

    fun listDetallePedidos(pedidoId:String){

        db.collection("detallepedido")
            .whereEqualTo("pedidoId", pedidoId)
            .get()
            .addOnSuccessListener { querySnapshot ->
                var newList = arrayListOf<DetallePedidoUser>()
                for(document in querySnapshot){
                    val data = document.data
                    val nProducto = data["nProducto"] as? String ?: ""
                    val cantidad = data["cantidad"] as? Number ?: 0
                    val pedidoId = data["pedidoId"] as? String ?: ""
                    val precio = data["precio"] as? Number ?: 0.00
                    val codProducto = data["codProducto"] as? String?:""

                    val modelo= DetallePedidoUser(nProducto,cantidad,pedidoId,precio,codProducto)
                    newList.add((modelo))
                }
                listDetallePedidos.value=newList
            }
    }
}