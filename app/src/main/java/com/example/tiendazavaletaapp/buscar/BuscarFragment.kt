package com.example.tiendazavaletaapp.buscar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.R

class BuscarFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_buscar,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val listSearch = listOf<Buscar>(
            Buscar("Marca 1","Producto 1","Cateogria",10.00, R.drawable.img),
            Buscar("Marca 2","Producto 2","Cateogria",20.00, R.drawable.img),
            Buscar("Marca 2","Producto 3","Cateogria",5.00, R.drawable.img),
            Buscar("Marca 3","Producto 4","Cateogria",25.00, R.drawable.img),
            Buscar("Marca 1","Producto 5","Cateogria",12.30, R.drawable.img),
            Buscar("Marca 1","Producto 5","Cateogria",12.30, R.drawable.img),
            Buscar("Marca 1","Producto 1","Cateogria",12.30, R.drawable.img),
            Buscar("Marca 1","Producto 5","Cateogria",12.30, R.drawable.img),
            Buscar("Marca 1","Producto 5","Cateogria",12.30, R.drawable.img),
            Buscar("Marca 1","Producto 4","Cateogria",12.30, R.drawable.img),
            Buscar("Marca 1","Producto 5","Cateogria",12.30, R.drawable.img),
        )

        val recyclerSearch =view.findViewById<RecyclerView>(R.id.recyclerSearch)


        val adapterW = BuscarAdapter(listSearch)
        recyclerSearch.adapter= adapterW
        recyclerSearch.layoutManager= GridLayoutManager(context,2)
    }

    companion object{
        fun newInstance() : BuscarFragment = BuscarFragment()
    }
}