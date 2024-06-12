package com.example.tiendazavaletaapp.Perfil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

import com.example.tiendazavaletaapp.R

class PerfilFragment :Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_perfil,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val miPerfil: LinearLayout = view.findViewById(R.id.miPerfil)

        miPerfil.setOnClickListener {
            // Aquí es donde realizas la navegación
            val newFragment = PerfilEditFragment.newInstance()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_menu, newFragment)
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            transaction.commit()
        }
    }

    companion object{
        fun newInstance() : PerfilFragment = PerfilFragment()
    }

}