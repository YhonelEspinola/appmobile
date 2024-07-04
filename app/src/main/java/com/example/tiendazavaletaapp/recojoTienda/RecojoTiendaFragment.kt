package com.example.tiendazavaletaapp.recojoTienda

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tiendazavaletaapp.R
import com.example.tiendazavaletaapp.detallePedidoRt.DetallePedidoRtActivitty
import com.google.android.material.textfield.TextInputEditText

class RecojoTiendaFragment : Fragment() {
    private lateinit var viewModel: RenTViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recogo_tienda, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[RenTViewModel::class.java]
        val btnContinuarRecojo : Button = view.findViewById(R.id.btnContinuarRecojo)
        val textNomApe : TextInputEditText = view.findViewById(R.id.textNomApe)
        val textDni : TextInputEditText = view.findViewById(R.id.textDni)


        viewModel.mensajeError.observe(viewLifecycleOwner) { error ->
            if (error.isNotEmpty()) {
                when (error) {
                    "Ingrese su nombre" -> textNomApe.error = error
                    "Ingrese su DNI" -> textDni.error = error
                }
            } else {
                textNomApe.error = null
                textDni.error = null
            }
        }
        btnContinuarRecojo.setOnClickListener {

            val nombre = textNomApe.text.toString()
            val dni = textDni.text.toString()

            if (viewModel.validarInfo(nombre, dni)) {
                val intent = Intent(activity, DetallePedidoRtActivitty::class.java).apply {
                    putExtra("NomApe", nombre)
                    putExtra("Dni", dni)
                }
                startActivity(intent)
            }
        }
    }

    companion object{
        fun recojoInstance() : RecojoTiendaFragment = RecojoTiendaFragment()
    }
}