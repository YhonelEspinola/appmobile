package com.example.tiendazavaletaapp.detalleProducto

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tiendazavaletaapp.R
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso

class DetalleProductoActivity: AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_producto)

        val imagen = findViewById<ImageView>(R.id.imgProducto)
        val textTitulo = findViewById<TextView>(R.id.textTitulo)
        val textMarca = findViewById<TextView>(R.id.textMarca)
        val textCategoria = findViewById<TextView>(R.id.textCategoria)
        val textPrecio = findViewById<TextView>(R.id.textPrecio)
        val textDescripcion = findViewById<TextView>(R.id.textDescripcion)
        val textCodigo = findViewById<TextView>(R.id.textCodigo)
        val textStock = findViewById<TextView>(R.id.textStock)

        val intent = intent

        val codProducto = intent.getStringExtra("codProducto")
        val nProducto = intent.getStringExtra("nProducto")
        val marca = intent.getStringExtra("marca")
        val categoria = intent.getStringExtra("categoria")
        val precio = intent.getDoubleExtra("precio",0.00)
        val imgProducto = intent.getStringExtra("imgProducto")
        val descripcion = intent.getStringExtra("descripcion")
        val stock = intent.getIntExtra("stock",0)

        textTitulo.text = nProducto
        textMarca.text = marca
        textCategoria.text = categoria
        textPrecio.text = String.format("%.2f",precio)
        textDescripcion.text = descripcion
        textStock.text = stock.toString()
        textCodigo.text = codProducto
        Picasso.get().load(imgProducto).into(imagen)

    }

}