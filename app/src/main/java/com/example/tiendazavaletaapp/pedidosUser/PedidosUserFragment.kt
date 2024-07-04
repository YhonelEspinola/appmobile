package com.example.tiendazavaletaapp.pedidosUser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.Perfil.PerfilFragment
import com.example.tiendazavaletaapp.R
import com.google.firebase.auth.FirebaseAuth

class PedidosUserFragment: Fragment(){
    private var firebaseAuth: FirebaseAuth? = null
    private lateinit var viewModel: PedidoViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_historial_pedidos_user,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[PedidoViewModel::class.java]

        val recyclerPedidosUser =view.findViewById<RecyclerView>(R.id.recyclerPedidosUser)

        val adapterPU = PedidosUserAdapter()
        recyclerPedidosUser.adapter= adapterPU
        recyclerPedidosUser.layoutManager= LinearLayoutManager(context)
        val retroceder: ImageView = view.findViewById(R.id.retroceder)

        retroceder.setOnClickListener {
            requireActivity().onBackPressed()
        }
        firebaseAuth = FirebaseAuth.getInstance()
        val userId = firebaseAuth?.currentUser?.uid
        viewModel.listPedidos(userId.toString())
        viewModel.listPedidos.observe(viewLifecycleOwner) {

            if (it.isNotEmpty()) {
                adapterPU.setDatos(it)
            }

        }
    }

    companion object{
        fun newInstance() : PedidosUserFragment = PedidosUserFragment()
    }

}