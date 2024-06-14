package com.example.tiendazavaletaapp.detallePedidoUser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.R
import com.example.tiendazavaletaapp.pedidosUser.PedidosUserAdapter

class DetallePedidoUserFragment:Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detalle_pedido_users,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val recyclerDetallesPedidosUser = view.findViewById<RecyclerView>(R.id.recyclerdetalles)

        val listDetallePedidosUser = listOf<DetallePedidoUser>(
            DetallePedidoUser("Cuaderno justus",10,"ss88",3.0),
            DetallePedidoUser("Cuaderno college",12,"ss88",5.0),
            DetallePedidoUser("Lapiz",10,"ss88",3.0),
            DetallePedidoUser("Libro ",12,"ss88",5.0),
            DetallePedidoUser("Borrador",10,"ss88",3.0),
            DetallePedidoUser("Mochila",12,"ss88",5.0),
        )

        val adapterPU = DetallePedididoUserAdapter(listDetallePedidosUser)
        recyclerDetallesPedidosUser.adapter= adapterPU
        recyclerDetallesPedidosUser.layoutManager= LinearLayoutManager(context)
    }

    companion object{
        fun newInstance() : DetallePedidoUserFragment = DetallePedidoUserFragment()
    }
}