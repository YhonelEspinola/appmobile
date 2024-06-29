package com.example.tiendazavaletaapp.buscar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.ProductosListaAdmin.ProductosListaAdmin
import com.example.tiendazavaletaapp.R
import com.example.tiendazavaletaapp.home.BuscarAdapter
import com.google.firebase.firestore.FirebaseFirestore

class BuscarFragment : Fragment() {
    private val db = FirebaseFirestore.getInstance()
    private val colleccion = db.collection("producto")
    private lateinit var adapterB: BuscarAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_buscar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerSearch = view.findViewById<RecyclerView>(R.id.recyclerSearch)
        val searchView = view.findViewById<SearchView>(R.id.search)
        adapterB = BuscarAdapter(emptyList())
        recyclerSearch.adapter = adapterB
        recyclerSearch.layoutManager = GridLayoutManager(context, 2)

        listBuscar()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { adapterB.filter(it) }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { adapterB.filter(it) }
                return false
            }
        })
    }

    companion object {
        fun newInstance(): BuscarFragment = BuscarFragment()
    }

    private fun listBuscar() {
        colleccion.get()
            .addOnSuccessListener { querySnapshot ->
                val listProductos = mutableListOf<ProductosListaAdmin>()
                for (document in querySnapshot) {
                    val nProducto = document.getString("nProducto") ?: ""
                    val marca = document.getString("marca") ?: ""
                    val categoria = document.getString("categoria") ?: ""
                    val codigo = document.id
                    val precio = document.getDouble("precio") ?: 0.0
                    val imgProducto = document.getString("imgProducto") ?: ""
                    val stock = document.getLong("stock")?.toInt() ?: 0
                    val descripcion = document.getString("descripcion") ?: ""
                    val modelo = ProductosListaAdmin(nProducto, marca, categoria, codigo, precio, imgProducto, stock, descripcion)
                    listProductos.add(modelo)
                }
                adapterB.setDatos(listProductos)
            }
    }
}