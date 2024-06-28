package com.example.tiendazavaletaapp.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.ProductosListaAdmin.ProductosListaAdmin
import com.example.tiendazavaletaapp.R
import com.google.firebase.firestore.FirebaseFirestore

class HomeSubfragment: Fragment() {
    private  val db= FirebaseFirestore.getInstance()
    private val colleccion= db.collection("producto")
    private lateinit var adapterMV: ProductosHomeAdapter
    private lateinit var adapterR: ProductosHomeAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.subfragment_home,container,false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerMasVendidos = view.findViewById<RecyclerView>(R.id.recyclerMasVendidos)
        val recyclerRecomendado = view.findViewById<RecyclerView>(R.id.recyclerRecomendado)

        adapterMV = ProductosHomeAdapter(emptyList())
        recyclerMasVendidos.adapter=adapterMV
        recyclerMasVendidos.layoutManager=
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)

        adapterR = ProductosHomeAdapter(emptyList())
        recyclerRecomendado.adapter=adapterR
        recyclerRecomendado.layoutManager=
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)

        listMasVendidos()

        listRecomendado()

    }

    companion object{
        fun newInstance() : HomeSubfragment = HomeSubfragment()
    }

    private fun listMasVendidos(){
        colleccion.get()
            .addOnSuccessListener { querySnapshot ->
                val listProductosListaAdmin = mutableListOf<ProductosListaAdmin>()
                for(document in querySnapshot){
                    val nProducto = document.getString("nProducto") ?: ""
                    val marca = document.getString("marca") ?: ""
                    val categoria = document.getString("categoria") ?: ""
                    val codigo = document.id
                    val precio = document.getDouble("precio") ?: 0.0
                    val imgProducto = document.getString("imgProducto") ?: ""
                    val stock = document.getLong("stock")?.toInt() ?: 0
                    val descripcion = document.getString("descripcion") ?: ""
                    if(document != null){
                        val modelo= ProductosListaAdmin(nProducto,marca,categoria,codigo,precio,imgProducto,stock,descripcion)
                        listProductosListaAdmin.add((modelo))
                    }
                }
                adapterMV.setDatos(listProductosListaAdmin)
            }
    }

    private fun listRecomendado(){
        colleccion.get()
            .addOnSuccessListener { querySnapshot ->
                val listProductosListaAdmin = mutableListOf<ProductosListaAdmin>()
                for(document in querySnapshot){
                    val nProducto = document.getString("nProducto") ?: ""
                    val marca = document.getString("marca") ?: ""
                    val categoria = document.getString("categoria") ?: ""
                    val codigo = document.id
                    val precio = document.getDouble("precio") ?: 0.0
                    val imgProducto = document.getString("imgProducto") ?: ""
                    val stock = document.getLong("stock")?.toInt() ?: 0
                    val descripcion = document.getString("descripcion") ?: ""
                    if(document != null){
                        val modelo= ProductosListaAdmin(nProducto,marca,categoria,codigo,precio,imgProducto,stock,descripcion)
                        listProductosListaAdmin.add((modelo))
                    }
                }
                adapterR.setDatos(listProductosListaAdmin)
            }
    }
}