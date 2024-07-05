package com.example.tiendazavaletaapp.carrito

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.R
import com.example.tiendazavaletaapp.login.LoginActivity
import com.example.tiendazavaletaapp.menuTop.MenuTopActivity
import com.example.tiendazavaletaapp.pagoEntrega.PagoEntregraFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CarritoFragment : Fragment() {

    private var firebaseAuth: FirebaseAuth? = null
    private val db = FirebaseFirestore.getInstance()
    private lateinit var adapterC: CarritoAdapter
    private lateinit var textTotal: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_carrito, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        firebaseAuth = FirebaseAuth.getInstance()

        textTotal = view.findViewById(R.id.textTotal)

        val recyclerCarrito = view.findViewById<RecyclerView>(R.id.recyclerCarrito)
        recyclerCarrito.layoutManager = LinearLayoutManager(context)

        val userId = firebaseAuth?.currentUser?.uid
        if (userId != null) {
            db.collection("carrito")
                .whereEqualTo("userId", userId)
                .get()
                .addOnSuccessListener { documents ->
                    val listCarrito = mutableListOf<Carrito>()
                    var totalSum = 0.0
                    for (document in documents) {
                        val item = document.toObject(Carrito::class.java)
                        listCarrito.add(item)
                        totalSum += item.precioTotal
                    }
                    textTotal.text = String.format("%.2f", totalSum)
                    adapterC = CarritoAdapter(listCarrito)
                    recyclerCarrito.adapter = adapterC


                    adapterC.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
                        override fun onChanged() {
                            super.onChanged()
                            updateTotal()
                        }

                        override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
                            super.onItemRangeChanged(positionStart, itemCount)
                            updateTotal()
                        }

                        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                            super.onItemRangeInserted(positionStart, itemCount)
                            updateTotal()
                        }

                        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                            super.onItemRangeRemoved(positionStart, itemCount)
                            updateTotal()
                        }
                    })
                    val swipeHandler = SwipeToDeleteCallback(adapterC, db)
                    val itemTouchHelper = ItemTouchHelper(swipeHandler)
                    itemTouchHelper.attachToRecyclerView(recyclerCarrito)
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(activity, "Error al obtener el carrito", Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(activity, "Debe iniciar sesión", Toast.LENGTH_SHORT).show()
        }

        val btnProcesarCompra: Button = view.findViewById(R.id.btnProcesarCompra)

        btnProcesarCompra.setOnClickListener {
                comprobarSesion()
        }

    }

    private fun updateTotal() {
        val userId = firebaseAuth?.currentUser?.uid
        if (userId != null) {
            db.collection("carrito")
                .whereEqualTo("userId", userId)
                .get()
                .addOnSuccessListener { documents ->
                    var totalSum = 0.0
                    for (document in documents) {
                        val item = document.toObject(Carrito::class.java)
                        totalSum += item.precioTotal
                    }
                    textTotal.text = String.format("%.2f", totalSum)
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(activity, "Error al actualizar el total", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun comprobarSesion() {
        if (firebaseAuth!!.currentUser == null) {
            Toast.makeText(activity, "Inicie sesión para poder seguir con la compra", Toast.LENGTH_SHORT).show()
        }
        else if(adapterC.itemCount == 0){
            Toast.makeText(activity, "El carrito está vacío", Toast.LENGTH_SHORT).show()
        }
        else {
            startActivity(Intent(activity, MenuTopActivity::class.java))
        }
    }

    companion object {
        fun newInstance(): CarritoFragment = CarritoFragment()
    }
}