package com.example.tiendazavaletaapp.pagoEntrega

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.tiendazavaletaapp.R
import com.example.tiendazavaletaapp.detallePedidoPce.DetallePedidoPceActivity

class PagoEntregraFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pago_entrega, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnContinuar : Button = view.findViewById(R.id.btnContinuar)
        btnContinuar.setOnClickListener {
            val intent = Intent(activity, DetallePedidoPceActivity::class.java)
            startActivity(intent)
        }
    }

    companion object{
        fun pagoentregaInstance() : PagoEntregraFragment = PagoEntregraFragment()
    }
}