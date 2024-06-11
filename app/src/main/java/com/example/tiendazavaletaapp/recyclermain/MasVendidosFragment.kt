package com.example.tiendazavaletaapp.recyclermain

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.R

class MasVendidosFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_masvendido,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerCategoria = view.findViewById<RecyclerView>(R.id.recyclerCategorias)
        val recyclerMasVendidos = view.findViewById<RecyclerView>(R.id.recyclerMasVendidos)
        val recyclerRecomendado = view.findViewById<RecyclerView>(R.id.recyclerRecomendado)

        val listCategoria = listOf<Categoria>(
            Categoria("Categoria1"),
            Categoria("Categoria2"),
            Categoria("Categoria3"),
            Categoria("Categoria4"),
            Categoria("Categoria5"),
        )
        val adapterC = CategoriaAdapter(listCategoria)
        recyclerCategoria.adapter=adapterC
        recyclerCategoria.layoutManager=
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)



        val listProductos = listOf<MasVendidos>(
            MasVendidos("Marca","Titulo","Cateogria",0.00,R.drawable.img),
            MasVendidos("Marca","Titulo","Cateogria",0.00,R.drawable.img),
            MasVendidos("Marca","Titulo","Cateogria",0.00,R.drawable.img),
            MasVendidos("Marca","Titulo","Cateogria",0.00,R.drawable.img),
            MasVendidos("Marca","Titulo","Cateogria",0.00,R.drawable.img),
        )
        val adapterMV = MasVendidosAdapter(listProductos)
        recyclerMasVendidos.adapter=adapterMV
        recyclerMasVendidos.layoutManager=
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)

        val adapterR = MasVendidosAdapter(listProductos)
        recyclerRecomendado.adapter=adapterR
        recyclerRecomendado.layoutManager=
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)

    }

    companion object{
        fun newInstance() : MasVendidosFragment = MasVendidosFragment()
    }
}