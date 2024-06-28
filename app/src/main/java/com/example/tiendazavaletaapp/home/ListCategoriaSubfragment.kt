package com.example.tiendazavaletaapp.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.ProductosListaAdmin.ProductosListaAdmin
import com.example.tiendazavaletaapp.R
import com.google.firebase.firestore.FirebaseFirestore

class ListCategoriaSubfragment: Fragment() {

    private  val db= FirebaseFirestore.getInstance()
    private val colleccion= db.collection("producto")
    private lateinit var adapterPLA: ListCategoriaAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.subfragment_categoriahome,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerPC = view.findViewById<RecyclerView>(R.id.recyclerPC)
        adapterPLA = ListCategoriaAdapter(emptyList())
        recyclerPC.adapter=adapterPLA
        recyclerPC.layoutManager= GridLayoutManager(context,2)


        val categoria = arguments?.getString("catselect").toString()
        listProductos(categoria)
    }

    companion object{
        fun newInstance() : ListCategoriaSubfragment = ListCategoriaSubfragment()
    }

    private fun listProductos(categoria: String){
        colleccion.whereEqualTo("categoria", categoria).get()
            .addOnSuccessListener { querySnapshot ->
                val listProductosLCat = mutableListOf<ProductosListaAdmin>()
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
                        listProductosLCat.add((modelo))
                    }
                }
                adapterPLA.setDatos(listProductosLCat)
            }
    }
}