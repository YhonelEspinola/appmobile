package com.example.tiendazavaletaapp.pagoEntrega

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
import com.example.tiendazavaletaapp.detallePedidoPce.DetallePedidoPceActivity
import com.example.tiendazavaletaapp.recojoTienda.RenTViewModel
import com.google.android.material.textfield.TextInputEditText

class PagoEntregraFragment : Fragment() {
    private lateinit var viewModel: PcEViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pago_entrega, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[PcEViewModel::class.java]
        val btnContinuar : Button = view.findViewById(R.id.btnContinuar)
        val textNomApe : TextInputEditText = view.findViewById(R.id.textNomApe)
        val textDni : TextInputEditText = view.findViewById(R.id.textDni)
        val textUbicacion : TextInputEditText = view.findViewById(R.id.textUbicacion)
        val textDirec : TextInputEditText = view.findViewById(R.id.textDirec)
        val textReferencia : TextInputEditText = view.findViewById(R.id.textReferencia)

        viewModel.mensajeError.observe(viewLifecycleOwner, { error ->
            if (error.isNotEmpty()) {
                when (error) {
                    "Ingrese su nombre" -> textNomApe.error = error
                    "Ingrese su DNI" -> textDni.error = error
                    "Ingrese la ubicación" -> textUbicacion.error = error
                    "Ingrese su dirección" -> textDirec.error = error
                    "Ingrese una referencia" -> textReferencia.error = error
                }
            } else {
                textNomApe.error = null
                textDni.error = null
                textUbicacion.error = null
                textDirec.error = null
                textReferencia.error = null
            }
        })
        btnContinuar.setOnClickListener {
            val nombre = textNomApe.text.toString()
            val Dni = textDni.text.toString()
            val Ubicacion = textUbicacion.text.toString()
            val Direc = textDirec.text.toString()
            val Referencia = textReferencia.text.toString()
            if (viewModel.validarInfo(nombre,Dni,Ubicacion,Direc,Referencia)) {
                val intent = Intent(activity, DetallePedidoPceActivity::class.java).apply {
                    putExtra("NomApe", textNomApe.text.toString())
                    putExtra("Dni", textDni.text.toString())
                    putExtra("Ubicacion", textUbicacion.text.toString())
                    putExtra("Direc", textDirec.text.toString())
                    putExtra("Referencia", textReferencia.text.toString())
                }
                startActivity(intent)
            }

        }
    }

    companion object{
        fun pagoentregaInstance() : PagoEntregraFragment = PagoEntregraFragment()
    }
}