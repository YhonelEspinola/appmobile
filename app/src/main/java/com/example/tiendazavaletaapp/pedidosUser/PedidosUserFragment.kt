package com.example.tiendazavaletaapp.pedidosUser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.Perfil.PerfilFragment
import com.example.tiendazavaletaapp.R

class PedidosUserFragment: Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_historial_pedidos_user,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val listPedidosUser = listOf<PedidosUser>(
            PedidosUser(true,"DireccionTienda", "01/05/2024", "phh88", 20.0),
            PedidosUser(false,"DireccionDomicilio", "08/05/2024", "phh99", 20.0),
            PedidosUser(true,"DireccionTienda", "01/05/2024", "phh88", 20.0),
            PedidosUser(false,"DireccionDomicilio", "08/05/2024", "phh99", 20.0),
            PedidosUser(true,"DireccionTienda", "01/05/2024", "phh88", 20.0),
            PedidosUser(false,"DireccionDomicilio", "08/05/2024", "phh99", 20.0),
            PedidosUser(true,"DireccionTienda", "01/05/2024", "phh88", 20.0),
            PedidosUser(false,"DireccionDomicilio", "08/05/2024", "phh99", 20.0),
        )

        val recyclerPedidosUser =view.findViewById<RecyclerView>(R.id.recyclerPedidosUser)

        val adapterPU = PedidosUserAdapter(listPedidosUser)
        recyclerPedidosUser.adapter= adapterPU
        recyclerPedidosUser.layoutManager= LinearLayoutManager(context)
        val retroceder: ImageView = view.findViewById(R.id.retroceder)

        retroceder.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    companion object{
        fun newInstance() : PedidosUserFragment = PedidosUserFragment()
    }

}