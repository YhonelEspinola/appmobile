package com.example.tiendazavaletaapp.carrito

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

        val btnProcesarCompra = view.findViewById<Button>(R.id.btnProcesarCompra)

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


        btnProcesarCompra.setOnClickListener {
            val newFragment = PagoEntregraFragment.pagoentregaInstance()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_menu2, newFragment)
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            transaction.commit()
        }
    }

    companion object{
        fun newInstance() : CarritoFragment = CarritoFragment()
    }
}