package com.example.tiendazavaletaapp.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.ProductosListaAdmin.ProductosListaAdmin
import com.example.tiendazavaletaapp.R
import com.google.firebase.firestore.FirebaseFirestore

class HomeSubfragment: Fragment() {
    private lateinit var adapterP: PopularAdapter
    private lateinit var adapterN: ProductosHomeAdapter
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
        val verTodo = view.findViewById<TextView>(R.id.verTodo)

        adapterP = PopularAdapter()
        recyclerMasVendidos.adapter=adapterP
        recyclerMasVendidos.layoutManager=
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)

        adapterN = ProductosHomeAdapter()
        recyclerRecomendado.adapter=adapterN
        recyclerRecomendado.layoutManager= GridLayoutManager(context,2)

        viewModel.listProductosHomeI()
        viewModel.listProductosMV.observe(viewLifecycleOwner) {

            if (it.isNotEmpty()) {
                adapterP.setDatos(it)
            }

        }

        viewModel.listProductosHomeII()
        viewModel.listProductosN.observe(viewLifecycleOwner) {

            if (it.isNotEmpty()) {
                adapterN.setDatos(it)
            }

        }
        verTodo.setOnClickListener(){

            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.subfragment_home, MasNuevoSubFragment.newInstance())
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            transaction.commit()
        }

    }

    companion object{
        fun newInstance() : HomeSubfragment = HomeSubfragment()
    }

}