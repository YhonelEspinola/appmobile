package com.example.tiendazavaletaapp.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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
    private lateinit var viewModel: ListCategoriaViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.subfragment_home,container,false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ListCategoriaViewModel::class.java]
        val recyclerMasVendidos = view.findViewById<RecyclerView>(R.id.recyclerMasVendidos)
        val recyclerRecomendado = view.findViewById<RecyclerView>(R.id.recyclerRecomendado)

        adapterMV = ProductosHomeAdapter()
        recyclerMasVendidos.adapter=adapterMV
        recyclerMasVendidos.layoutManager=
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)

        adapterR = ProductosHomeAdapter()
        recyclerRecomendado.adapter=adapterR
        recyclerRecomendado.layoutManager=
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)

        viewModel.listProductosHomeI("")
        viewModel.listProductosMV.observe(viewLifecycleOwner) {

            if (it.isNotEmpty()) {
                adapterMV.setDatos(it)
            }

        }

        viewModel.listProductosHomeII("")
        viewModel.listProductosR.observe(viewLifecycleOwner) {

            if (it.isNotEmpty()) {
                adapterR.setDatos(it)
            }

        }
    }

    companion object{
        fun newInstance() : HomeSubfragment = HomeSubfragment()
    }

}