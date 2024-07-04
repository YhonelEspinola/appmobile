package com.example.tiendazavaletaapp.detallePedidoUser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.R
import com.example.tiendazavaletaapp.pedidosUser.PedidoViewModel
import com.example.tiendazavaletaapp.pedidosUser.PedidosUserAdapter

class DetallePedidoUserFragment:Fragment() {
    private lateinit var viewModel: DetallePedidoUserViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detalle_pedido_users,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[DetallePedidoUserViewModel::class.java]

        val recyclerDetallesPedidosUser = view.findViewById<RecyclerView>(R.id.recyclerdetalles)
        val tipoPedido = view.findViewById<TextView>(R.id.tipoPedido)
        val textDireccion = view.findViewById<TextView>(R.id.textDireccion)
        val textFechaCompra = view.findViewById<TextView>(R.id.textFechaCompra)
        val textCodPedido = view.findViewById<TextView>(R.id.textCodPedido)
        val textNombreApe = view.findViewById<TextView>(R.id.textNombreApe)
        val textDni = view.findViewById<TextView>(R.id.textDni)
        val textCantProductosTotal = view.findViewById<TextView>(R.id.textCantProductosTotal)
        val textTotal = view.findViewById<TextView>(R.id.textTotal)
        val textFechaEstimada = view.findViewById<TextView>(R.id.textFechaEstimada)

        val adapterPU = DetallePedididoUserAdapter()
        recyclerDetallesPedidosUser.adapter= adapterPU
        recyclerDetallesPedidosUser.layoutManager= LinearLayoutManager(context)

        val retroceder: ImageView = view.findViewById(R.id.retroceder)

        retroceder.setOnClickListener {
            requireActivity().onBackPressed()
        }

        tipoPedido.text=arguments?.getString("tipodecompra")
        textDireccion.text=arguments?.getString("direccion")
        textFechaCompra.text=arguments?.getString("fecha")
        textTotal.text=arguments?.getString("total")
        textCantProductosTotal.text=arguments?.getString("cantTotalProduct")
        textFechaEstimada.text=arguments?.getString("fechaestimada")
        textNombreApe.text=arguments?.getString("nombreApe")
        textDni.text=arguments?.getString("dni")

        val pedidoId = arguments?.getString("pedidoId")

        textCodPedido.text=pedidoId

        viewModel.listDetallePedidos(pedidoId.toString())
        viewModel.listDetallePedidos.observe(viewLifecycleOwner) {

            if (it.isNotEmpty()) {
                adapterPU.setDatos(it)
            }

        }
    }

    companion object{
        fun newInstance() : DetallePedidoUserFragment = DetallePedidoUserFragment()
    }
}