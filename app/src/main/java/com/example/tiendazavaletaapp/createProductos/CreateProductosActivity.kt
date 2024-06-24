package com.example.tiendazavaletaapp.createProductos

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.tiendazavaletaapp.R
import com.example.tiendazavaletaapp.gestionAdmid.GestionAdminActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.util.UUID

class CreateProductosActivity : AppCompatActivity() {

    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()){ uri->
        if(uri != null){
            imgProducto.setImageURI(uri)
            selectedImageUri = uri
        }else{
            Log.i("ari","img no seleccionada")
        }
    }
    lateinit var btnImagen: Button
    lateinit var imgProducto: ImageView
    private var selectedImageUri: Uri? = null
    private val storage = Firebase.storage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_productos)

        imgProducto = findViewById(R.id.imgProducto)

        val edtMarca: AutoCompleteTextView = findViewById(R.id.edtMarca)
        val edtCategoria: AutoCompleteTextView = findViewById(R.id.edtCategoria)

        val items = listOf("Utiles de oficina", "Papeleria", "Arte y Diseño", "Mochilas","Cuadernos, Libretas","Textos Escolares")
        val adapter = ArrayAdapter(this, R.layout.item_list_dropdown, items)
        edtCategoria.setAdapter(adapter)

        val itemsMarc = listOf("ARTESCO","EPSON","FABER CASTELL","JUSTUS","PILOT","STABILO","VINIFAN","LAYCONSA","OTRO")
        val adapterM = ArrayAdapter(this, R.layout.item_list_dropdown, itemsMarc)
        edtMarca.setAdapter(adapterM)

        btnImagen = findViewById(R.id.btnImagen)
        btnImagen.setOnClickListener{
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        val btnCrearProductos = findViewById<Button>(R.id.btnCrearProductos)
        btnCrearProductos.setOnClickListener{
            if (selectedImageUri != null) {
                uploadImageToStorage()
            } else {
                Toast.makeText(this, "Por favor, selecciona una imagen", Toast.LENGTH_SHORT).show()
            }

        }


        val btnButtonAtras = findViewById<ImageView>(R.id.btnButtonAtras)
        btnButtonAtras.setOnClickListener{
            val intent = Intent(this, GestionAdminActivity::class.java)
            startActivity(intent)
        }

    }
    private fun uploadImageToStorage() {
        val filename = UUID.randomUUID().toString()
        val ref = storage.reference.child("$filename")
        val uploadTask = selectedImageUri?.let { ref.putFile(it) }

        uploadTask?.addOnSuccessListener {
            ref.downloadUrl.addOnSuccessListener { uri ->
                saveProductToFirestore(uri.toString())
            }
        }?.addOnFailureListener {
            Toast.makeText(this, "Error al subir la imagen", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveProductToFirestore(imageUrl: String) {
        val db = FirebaseFirestore.getInstance()
        val edtTitulo: TextInputEditText = findViewById(R.id.edtTitulo)
        val edtMarca: AutoCompleteTextView = findViewById(R.id.edtMarca)
        val edtCategoria: AutoCompleteTextView = findViewById(R.id.edtCategoria)
        val edtPrecio: TextInputEditText = findViewById(R.id.edtPrecio)
        val edtDescripcion: TextInputEditText = findViewById(R.id.edtDescripcion)
        val edtStock: TextInputEditText = findViewById(R.id.edtStock)

        val nProducto = edtTitulo.text.toString()
        val marca = edtMarca.text.toString()
        val categoria = edtCategoria.text.toString()
        val precio = edtPrecio.text.toString().toDouble()
        val descripcion = edtDescripcion.text.toString()
        val stock = edtStock.text.toString().toInt()

        val data = hashMapOf(
            "nProducto" to nProducto,
            "marca" to marca,
            "categoria" to categoria,
            "precio" to precio,
            "descripcion" to descripcion,
            "stock" to stock,
            "imgProducto" to imageUrl
        )

        db.collection("producto")
            .add(data)
            .addOnSuccessListener {
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_LONG).show()
                clearFields()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Algo salió mal", Toast.LENGTH_SHORT).show()
            }
    }
    private fun clearFields() {
        val edtTitulo: TextInputEditText = findViewById(R.id.edtTitulo)
        val edtMarca: AutoCompleteTextView = findViewById(R.id.edtMarca)
        val edtCategoria: AutoCompleteTextView = findViewById(R.id.edtCategoria)
        val edtPrecio: TextInputEditText = findViewById(R.id.edtPrecio)
        val edtDescripcion: TextInputEditText = findViewById(R.id.edtDescripcion)
        val edtStock: TextInputEditText = findViewById(R.id.edtStock)
        val imgProducto: ImageView = findViewById(R.id.imgProducto)

        edtTitulo.text?.clear()
        edtMarca.text?.clear()
        edtCategoria.text?.clear()
        edtPrecio.text?.clear()
        edtDescripcion.text?.clear()
        edtStock.text?.clear()
        imgProducto.setImageResource(0)
        selectedImageUri = null
    }
}