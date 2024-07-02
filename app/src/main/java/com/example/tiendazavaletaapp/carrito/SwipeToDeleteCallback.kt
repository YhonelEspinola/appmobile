package com.example.tiendazavaletaapp.carrito

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class SwipeToDeleteCallback (
    private val adapter: CarritoAdapter,
    private val db: FirebaseFirestore
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        val carrito = adapter.getItem(position)

        db.collection("carrito").document(carrito.userId + "_" + carrito.codProducto)
            .delete()
            .addOnSuccessListener {
                adapter.removeItem(position)
            }
    }
}