package com.example.tiendazavaletaapp.carrito

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
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
    private var btnEliminar: Button? = null

    private val db = FirebaseFirestore.getInstance()

    init {
        imgCarrito = itemView.findViewById(R.id.imgCarrito)
        textNombre = itemView.findViewById(R.id.textNombre)
        textMarca = itemView.findViewById(R.id.textMarca)
        textPrecio = itemView.findViewById(R.id.textPrecio)
        textCantidad = itemView.findViewById(R.id.textCantidad)
        btnIncrement = itemView.findViewById(R.id.btnIncrement)
        btnDecrement = itemView.findViewById(R.id.btnDecrement)
        btnEliminar = itemView.findViewById(R.id.btnEliminar)
    }

    fun bind(carrito: Carrito, adapter: CarritoAdapter) {
        textNombre?.text = carrito.nombre
        textMarca?.text = carrito.marca
        textPrecio?.text = String.format("%.2f", carrito.precioTotal)
        textCantidad?.text = carrito.cantidad.toString()

        Picasso.get().load(carrito.imgProducto).into(imgCarrito)

        btnIncrement?.setOnClickListener {
            val newQuantity = carrito.cantidad + 1
            updateCarrito(carrito, newQuantity, adapter)
        }

        btnDecrement?.setOnClickListener {
            if (carrito.cantidad > 1) {
                val newQuantity = carrito.cantidad - 1
                updateCarrito(carrito, newQuantity, adapter)
            }
        }

        btnEliminar?.setOnClickListener {
            db.collection("carrito").document(carrito.userId + "_" + carrito.codProducto)
                .delete()
                .addOnSuccessListener {
                    adapter.removeItem(adapterPosition)
                }
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