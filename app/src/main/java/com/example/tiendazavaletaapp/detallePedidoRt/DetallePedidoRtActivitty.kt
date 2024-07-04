package com.example.tiendazavaletaapp.detallePedidoRt

import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.Perfil.PerfilViewModel
import com.example.tiendazavaletaapp.R
import com.example.tiendazavaletaapp.detallePedidoPce.PedidoPceAdapter
import com.example.tiendazavaletaapp.detallePedidoPce.DetallePedidoViewModel
import com.example.tiendazavaletaapp.menu.MenuActivity
import com.google.firebase.auth.FirebaseAuth
import java.util.Locale

class DetallePedidoRtActivitty : AppCompatActivity() {
    private var firebaseAuth: FirebaseAuth? = null
    private lateinit var detallePedidoViewModel: DetallePedidoViewModel
    private lateinit var viewModelUser: PerfilViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_pedido_rt)

        viewModelUser = ViewModelProvider(this)[PerfilViewModel::class.java]
        detallePedidoViewModel = ViewModelProvider(this)[DetallePedidoViewModel::class.java]
        firebaseAuth = FirebaseAuth.getInstance()

        val btnRealizarPedido = findViewById<Button>(R.id.btnRealizarPedido)
        val userId = firebaseAuth?.currentUser?.uid
        val textPrecioTotal = findViewById<TextView>(R.id.textPrecioTotal)
        val fechaLlegada = findViewById<TextView>(R.id.fechaLlegada)
        val textUbicacion = findViewById<TextView>(R.id.textUbicacion)
        val textDirec = findViewById<TextView>(R.id.textDirec)
        val textRef = findViewById<TextView>(R.id.textDirec)
        val cantProductos = findViewById<TextView>(R.id.cantProductos)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler)

        val NomApe = intent.getStringExtra("NomApe")
        val Dni = intent.getStringExtra("Dni")
        val Referencia = textRef.text.toString()

        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, 7)

        // Formatea la fecha
        val dateFormat = SimpleDateFormat("d 'de' MMM.", Locale("es", "ES"))
        val fechaFormateada = dateFormat.format(calendar.time)
        fechaLlegada.text=fechaFormateada

        val maxHeight = 600 // 200dp en píxeles (aproximadamente)

        recyclerView.viewTreeObserver.addOnGlobalLayoutListener {
            if (recyclerView.height > maxHeight) {
                recyclerView.layoutParams.height = maxHeight
                recyclerView.requestLayout()
            }
        }

        val adapterPce = PedidoPceAdapter()
        recyclerView.adapter=adapterPce
        recyclerView.layoutManager= LinearLayoutManager(this)

        detallePedidoViewModel.listPce(userId.toString())
        detallePedidoViewModel.listPc.observe(this) {

            if (it.isNotEmpty()) {
                adapterPce.setDatos(it)
            }

        }

        detallePedidoViewModel.totalPrecio.observe(this) { total ->
            textPrecioTotal.text = String.format("%.2f", total) // Actualiza el TextView con el total del precio
        }
        detallePedidoViewModel.totalCantidad.observe(this) { cantidad ->
            cantProductos.text = cantidad.toString()
        }

        val btnCancela1 = findViewById<Button>(R.id.btnCancela)

        btnCancela1.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

        viewModelUser.leerInformacion()
        var email:String = ""
        viewModelUser.correoU.observe(this) {
            email = it.toString()
        }

        btnRealizarPedido.setOnClickListener {
            val tipoCompra:String = "Recojo en tienda:"

            detallePedidoViewModel.guardarPedido(
                cantProductos.text.toString(),
                textDirec.text.toString(),
                Dni.toString(),
                NomApe.toString(),
                Referencia,
                tipoCompra,
                textPrecioTotal.text.toString(),
                textUbicacion.text.toString(),
                userId.toString(),
                email,
                fechaFormateada
            )
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }
}