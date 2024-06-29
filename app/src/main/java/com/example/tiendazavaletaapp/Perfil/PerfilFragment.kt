package com.example.tiendazavaletaapp.Perfil

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

import com.example.tiendazavaletaapp.R
import com.example.tiendazavaletaapp.menu.MenuActivity
import com.example.tiendazavaletaapp.pedidosUser.PedidosUserFragment
import com.example.tiendazavaletaapp.vermas.VerMasFragment
import com.google.firebase.auth.FirebaseAuth

class PerfilFragment :Fragment(){

    private var  firebaseAuth: FirebaseAuth?=null

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

        val miPerfil: LinearLayout = view.findViewById(R.id.miPerfil)
        val misCompras: LinearLayout = view.findViewById(R.id.misCompras)
        val verMas: LinearLayout = view.findViewById(R.id.verMas)
        val cerarSesion : AppCompatButton = view.findViewById(R.id.logout_button)

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