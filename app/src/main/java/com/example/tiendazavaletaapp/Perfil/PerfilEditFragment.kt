package com.example.tiendazavaletaapp.Perfil

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tiendazavaletaapp.R

class PerfilEditFragment :Fragment(){

    private lateinit var viewModel: PerfilViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_perfil,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[PerfilViewModel::class.java]

        val username : EditText = view.findViewById(R.id.edit_username)
        val email : EditText = view.findViewById(R.id.edit_email)

        // Observar los cambios en el LiveData
        viewModel.nombreU.observe(viewLifecycleOwner, Observer { nombre ->
            username.setText(nombre)
        })
        viewModel.correoU.observe(viewLifecycleOwner, Observer { correo ->
            email.setText(correo)
        })
        // Llamar a la función para leer la información del usuario
        viewModel.leerInformacion()

        val btnGuardar : Button = view.findViewById(R.id.save_button)
        btnGuardar.setOnClickListener {
            val nuevoNombre = username.text.toString().trim()

            Toast.makeText(context, "Información actualizada exitosamente", Toast.LENGTH_SHORT).show()

            viewModel.actualizarInformacion(nuevoNombre)

            openSubFragment(PerfilFragment.newInstance())
        }

        val editPassword: EditText = view.findViewById(R.id.edit_password)
        editPassword.setOnClickListener {
            val intent = Intent(activity, CambiarContraPerfilActivity::class.java)
            startActivity(intent)
        }
        val retroceder: ImageView = view.findViewById(R.id.retroceder)

        retroceder.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun openSubFragment(fragment: Fragment) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_menu, fragment)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    companion object{
        fun newInstance() : PerfilEditFragment = PerfilEditFragment()
    }
}