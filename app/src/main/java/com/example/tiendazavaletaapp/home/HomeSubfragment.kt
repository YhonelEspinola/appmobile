package com.example.tiendazavaletaapp.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.R

class HomeSubfragment: Fragment() {
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

        val listProductos = listOf<ProductosHome>(
            ProductosHome("Marca","Titulo","Cateogria",0.00,R.drawable.img),
            ProductosHome("Marca","Titulo","Cateogria",0.00,R.drawable.img),
            ProductosHome("Marca","Titulo","Cateogria",0.00,R.drawable.img),
            ProductosHome("Marca","Titulo","Cateogria",0.00,R.drawable.img),
            ProductosHome("Marca","Titulo","Cateogria",0.00,R.drawable.img),
        )
        val adapterMV = ProductosHomeAdapter(listProductos)
        recyclerMasVendidos.adapter=adapterMV
        recyclerMasVendidos.layoutManager=
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)

        val adapterR = ProductosHomeAdapter(listProductos)
        recyclerRecomendado.adapter=adapterR
        recyclerRecomendado.layoutManager=
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)

    }

    companion object{
        fun newInstance() : HomeSubfragment = HomeSubfragment()
    }
}