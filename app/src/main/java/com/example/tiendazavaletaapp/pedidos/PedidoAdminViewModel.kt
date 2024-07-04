package com.example.tiendazavaletaapp.pedidos

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tiendazavaletaapp.pedidosUser.PedidosUser
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class PedidoAdminViewModel: ViewModel() {
    private  val db= FirebaseFirestore.getInstance()
    val listPedidos = MutableLiveData<List<PedidosUser>>()

    fun listPedidos(){

        db.collection("pedido")
            .orderBy("fecha", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { querySnapshot ->
                var newList = arrayListOf<PedidosUser>()
                for(document in querySnapshot){
                    val data = document.data
                    val cantTotalProduct = data["cantTotalProduct"] as? String ?: ""
                    val direccion = data["direccion"] as? String ?: ""
                    val dni = data["dni"] as? String ?: ""
                    val email = data["email"] as? String ?: ""
                    val fecha = data["fecha"] as? Timestamp
                    val fechaestimada = data["fechaestimada"] as? String?: ""
                    val nombreApe = data["nombreApe"] as? String ?: ""
                    val referencia = data["referencia"] as? String ?: ""
                    val tipodecompra = data["tipodecompra"] as? String ?: ""
                    val total = data["total"] as? String ?: ""
                    val ubicacion = data["ubicacion"] as? String ?: ""
                    val uid = data["uid"] as? String ?: ""
                    val idpedido = document.id

                    val modelo= PedidosUser(cantTotalProduct,direccion,dni,email,fecha,fechaestimada,nombreApe,referencia,tipodecompra,total,ubicacion,uid,idpedido)
                    newList.add((modelo))
                }
                listPedidos.value=newList
            }
    }
}