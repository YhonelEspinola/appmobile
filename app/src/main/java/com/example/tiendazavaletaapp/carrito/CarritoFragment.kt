package com.example.tiendazavaletaapp.carrito

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.R
import com.example.tiendazavaletaapp.menuTop.MenuTopActivity
import com.example.tiendazavaletaapp.pagoEntrega.PagoEntregraFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

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

        val btnProcesarCompra : Button = view.findViewById(R.id.btnProcesarCompra)
        btnProcesarCompra.setOnClickListener {
            val intent = Intent(activity, MenuTopActivity::class.java)
            startActivity(intent)
        }

    }

    companion object{
        fun newInstance() : CarritoFragment = CarritoFragment()
    }
}