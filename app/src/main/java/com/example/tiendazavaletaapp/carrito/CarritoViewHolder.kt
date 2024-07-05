package com.example.tiendazavaletaapp.carrito

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.R
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso

class CarritoViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup) : RecyclerView.ViewHolder(inflater.inflate(R.layout.item_carrito, viewGroup, false)) {

    private var imgCarrito: ImageView? = null
    private var textNombre: TextView? = null
    private var textMarca: TextView? = null
    private var textPrecio: TextView? = null
    private var textCantidad: TextView? = null
    private var btnIncrement: Button? = null
    private var btnDecrement: Button? = null

    private val db = FirebaseFirestore.getInstance()

    init {
        imgCarrito = itemView.findViewById(R.id.imgCarrito)
        textNombre = itemView.findViewById(R.id.textNombre)
        textMarca = itemView.findViewById(R.id.textMarca)
        textPrecio = itemView.findViewById(R.id.textPrecio)
        textCantidad = itemView.findViewById(R.id.textCantidad)
        btnIncrement = itemView.findViewById(R.id.btnIncrement)
        btnDecrement = itemView.findViewById(R.id.btnDecrement)
    }

    fun bind(carrito: Carrito, adapter: CarritoAdapter) {
        textNombre?.text = carrito.nombre
        textMarca?.text = carrito.marca
        textPrecio?.text = String.format("%.2f", carrito.precioTotal)
        textCantidad?.text = carrito.cantidad.toString()

        Picasso.get().load(carrito.imgProducto).into(imgCarrito)

        btnIncrement?.setOnClickListener {
            stockAlcanzado(carrito, carrito.cantidad + 1, adapter)


        }

        btnDecrement?.setOnClickListener {
            if (carrito.cantidad > 1) {
                val newQuantity = carrito.cantidad - 1
                updateCarrito(carrito, newQuantity, adapter)
            }
        }

    }
    private fun stockAlcanzado(carrito: Carrito, newQuantity: Int, adapter: CarritoAdapter) {
        db.collection("producto").document(carrito.codProducto)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val stock = document.getLong("stock")?.toInt() ?: 0
                    if (newQuantity <= stock) {
                        updateCarrito(carrito, newQuantity, adapter)
                    } else {
                        Toast.makeText(itemView.context, "No hay suficiente stock disponible", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(itemView.context, "Producto no encontrado", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(itemView.context, "Error al obtener el stock", Toast.LENGTH_SHORT).show()
            }
    }
    private fun updateCarrito(carrito: Carrito, newQuantity: Int, adapter: CarritoAdapter) {
        val newTotalPrice = newQuantity * carrito.precio
        db.collection("carrito").document(carrito.userId + "_" + carrito.codProducto)
            .update(
                mapOf(
                    "cantidad" to newQuantity,
                    "precioTotal" to newTotalPrice
                )
            )
            .addOnSuccessListener {
                carrito.cantidad = newQuantity
                carrito.precioTotal = newTotalPrice
                adapter.notifyItemChanged(adapterPosition)
            }
    }
}