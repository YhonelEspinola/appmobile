package com.example.tiendazavaletaapp.detallePedidoRt

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.tiendazavaletaapp.R
import com.example.tiendazavaletaapp.menu.MenuActivity

class DetallePedidoRtActivitty : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_pedido_rt)

        val btnCancela1 = findViewById<Button>(R.id.btnCancel)

        btnCancela1.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }
}