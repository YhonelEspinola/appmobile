package com.example.tiendazavaletaapp.Perfil

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.example.tiendazavaletaapp.R
import com.example.tiendazavaletaapp.menu.MenuActivity
import com.example.tiendazavaletaapp.pedidosUser.PedidosUserFragment
import com.example.tiendazavaletaapp.vermas.VerMasFragment
import com.google.firebase.auth.FirebaseAuth

class PerfilFragment :Fragment(){

    private var  firebaseAuth: FirebaseAuth?=null

    private lateinit var viewModel: PerfilViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_perfil,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()

        viewModel = ViewModelProvider(requireActivity()).get(PerfilViewModel::class.java)

        val miPerfil: LinearLayout = view.findViewById(R.id.miPerfil)
        val misCompras: LinearLayout = view.findViewById(R.id.misCompras)
        val verMas: LinearLayout = view.findViewById(R.id.verMas)
        val cerarSesion : AppCompatButton = view.findViewById(R.id.logout_button)

        val nombreUsuario = view.findViewById<TextView>(R.id.user_name)

        // Observar los cambios en el LiveData
        viewModel.nombreU.observe(viewLifecycleOwner, Observer { nombre ->
            nombreUsuario.text = nombre
        })
        // Llamar a la función para leer la información del usuario
        viewModel.leerInformacion()


        miPerfil.setOnClickListener {
            val newFragment = PerfilEditFragment.newInstance()
            openSubFragment(newFragment)
        }
        misCompras.setOnClickListener {
            val newFragment = PedidosUserFragment.newInstance()
            openSubFragment(newFragment)
        }
        verMas.setOnClickListener {
            val newFragment = VerMasFragment.newInstance()
            openSubFragment(newFragment)
        }
        cerarSesion.setOnClickListener {
            cerrarSesionM()
        }
    }
    private fun openSubFragment(fragment: Fragment) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_menu, fragment)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun cerrarSesionM(){
        firebaseAuth!!.signOut()
        startActivity(Intent(activity,MenuActivity::class.java))
        activity?.finish()
    }

    companion object{
        fun newInstance() : PerfilFragment = PerfilFragment()
    }

}