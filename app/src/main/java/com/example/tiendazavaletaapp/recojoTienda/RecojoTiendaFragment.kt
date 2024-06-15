package com.example.tiendazavaletaapp.recojoTienda

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.tiendazavaletaapp.R
import com.example.tiendazavaletaapp.detallePedidoPce.DetallePedidoPceActivity
import com.example.tiendazavaletaapp.detallePedidoRt.DetallePedidoRtActivitty

class RecojoTiendaFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recogo_tienda, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnContinuarRecojo : Button = view.findViewById(R.id.btnContinuarRecojo)
        btnContinuarRecojo.setOnClickListener {
            val intent = Intent(activity, DetallePedidoRtActivitty::class.java)
            startActivity(intent)
        }
    }

    companion object{
        fun recojoInstance() : RecojoTiendaFragment = RecojoTiendaFragment()
    }
}