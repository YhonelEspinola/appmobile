package com.example.tiendazavaletaapp.detalleProducto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tiendazavaletaapp.R
import com.example.tiendazavaletaapp.carrito.Carrito
import com.example.tiendazavaletaapp.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso

class DetalleProductoActivity: AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_producto)

        firebaseAuth = FirebaseAuth.getInstance()

        val imagen = findViewById<ImageView>(R.id.imgProducto)
        val textTitulo = findViewById<TextView>(R.id.textTitulo)
        val textMarca = findViewById<TextView>(R.id.textMarca)
        val textCategoria = findViewById<TextView>(R.id.textCategoria)
        val textPrecio = findViewById<TextView>(R.id.textPrecio)
        val textDescripcion = findViewById<TextView>(R.id.textDescripcion)
        val textStock = findViewById<TextView>(R.id.textStock)
        val btnAgregarCarrito = findViewById<Button>(R.id.btnAgregarCarrito)

        val intent = intent

        val codProducto = intent.getStringExtra("codigo")
        if (codProducto == null) {
            Toast.makeText(this, "Código de producto no encontrado", Toast.LENGTH_SHORT).show()
            finish()
            return
        }
        val nProducto = intent.getStringExtra("nProducto")
        val marca = intent.getStringExtra("marca")
        val categoria = intent.getStringExtra("categoria")
        val precio = intent.getDoubleExtra("precio", 0.00)
        val imgProducto = intent.getStringExtra("imgProducto")
        val descripcion = intent.getStringExtra("descripcion")
        val stock = intent.getIntExtra("stock", 0)

        textTitulo.text = nProducto
        textMarca.text = marca
        textCategoria.text = categoria
        textPrecio.text = String.format("%.2f", precio)
        textDescripcion.text = descripcion
        textStock.text = stock.toString()

        Picasso.get().load(imgProducto).into(imagen)

        btnAgregarCarrito.setOnClickListener {
            if (firebaseAuth.currentUser == null) {
                Toast.makeText(this, "Necesita iniciar sesion", Toast.LENGTH_SHORT).show()
            }else{
            agregarAlCarrito(
                codProducto,
                nProducto,
                marca,
                precio,
                imgProducto
            )}
        }
    }

    private fun agregarAlCarrito(
        codProducto: String,
        nProducto: String?,
        marca: String?,
        precio: Double,
        imgProducto: String?,
    ) {
        val userId = firebaseAuth.currentUser?.uid

        if (userId != null) {
            val carritoRef = db.collection("carrito").document(userId + "_" + codProducto)
            carritoRef.get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        val currentQuantity = document.getLong("cantidad") ?: 0
                        val newQuantity = currentQuantity + 1
                        val newTotalPrice = newQuantity * precio

                        carritoRef.update(
                            mapOf(
                                "cantidad" to newQuantity,
                                "precioTotal" to newTotalPrice
                            )
                        )
                    } else {
                        val carritoItem = Carrito(
                            precioTotal = precio,
                            cantidad = 1,
                            nombre = nProducto ?: "",
                            marca = marca ?: "",
                            precio = precio,
                            imgProducto = imgProducto ?: "",
                            userId = userId,
                            codProducto = codProducto
                        )
                        carritoRef.set(carritoItem)
                    }
                }
                .addOnSuccessListener {
                    Toast.makeText(this, "Producto agregado correctamente", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Error al agregar el producto", Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(this, "Error: Usuario no válido", Toast.LENGTH_SHORT).show()
        }
    }
}