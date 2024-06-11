package com.example.tiendazavaletaapp.carrito

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.R

class CarritoFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_carrito,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val listCarrito = listOf<Carrito>(
            Carrito("La Odisea","...",12.50,2,R.drawable.logo),
            Carrito("La Odisea","...",12.50,2,R.drawable.logo),
            Carrito("La Odisea","...",12.50,2,R.drawable.logo),
            Carrito("La Odisea","...",12.50,2,R.drawable.logo),
            Carrito("La Odisea","...",12.50,2,R.drawable.logo),
            Carrito("La Odisea","...",12.50,2,R.drawable.logo)
        )

        val recyclerCarrito =view.findViewById<RecyclerView>(R.id.recyclerCarrito)

        val adapterC = CarritoAdapter(listCarrito)
        recyclerCarrito.adapter= adapterC
        recyclerCarrito.layoutManager= LinearLayoutManager(context)
    }

    companion object{
        fun newInstance() : CarritoFragment = CarritoFragment()
    }
}