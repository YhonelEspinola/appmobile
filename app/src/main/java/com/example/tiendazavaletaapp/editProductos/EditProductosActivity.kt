package com.example.tiendazavaletaapp.editProductos

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.tiendazavaletaapp.ProductosListaAdmin.ProductosListaAdminActivity
import com.example.tiendazavaletaapp.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.squareup.picasso.Picasso
import java.util.UUID

class EditProductosActivity :AppCompatActivity(){

    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()){ uri->
        if(uri != null){
            imgProducto.setImageURI(uri)
            selectedImageUri = uri
        }else{
            Log.i("ari","img no seleccionada")
        }
    }
    private var selectedImageUri: Uri? = null
    private val storage = Firebase.storage

    private val db = FirebaseFirestore.getInstance()

    lateinit var btnImagen: Button
    lateinit var imgProducto: ImageView
    lateinit var edtTitulo: TextInputEditText
    lateinit var edtMarca: AutoCompleteTextView
    lateinit var edtCategoria: AutoCompleteTextView
    lateinit var edtPrecio: TextInputEditText
    lateinit var edtStock: TextInputEditText
    lateinit var edtDescripcion: TextInputEditText
    lateinit var textCodigo: TextView
    private lateinit var btnEditProductos: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_producto)

        btnImagen = findViewById(R.id.btnImagen)
        imgProducto = findViewById(R.id.imgProducto)
        edtTitulo = findViewById(R.id.edtTitulo)
        edtMarca = findViewById(R.id.edtMarca)
        edtCategoria = findViewById(R.id.edtCategoria)
        edtPrecio = findViewById(R.id.edtPrecio)
        edtStock = findViewById(R.id.edtStock)
        edtDescripcion = findViewById(R.id.edtDescripcion)
        textCodigo = findViewById(R.id.textCodigo)
        btnEditProductos = findViewById(R.id.btnEditProductos)

        val itemsCat = listOf("Utiles de oficina", "Papeleria", "Arte y Diseño", "Mochilas","Cuadernos, Libretas","Textos Escolares")
        val adapterC = ArrayAdapter(this, R.layout.item_list_dropdown, itemsCat)
        edtCategoria.setAdapter(adapterC)

        val itemsMarc = listOf("ARTESCO","CLASS&WORK","EPSON","FABER CASTELL","GENÉRICO","JUSTUS","PILOT","STABILO","VINIFAN","LAYCONSA","OTRO")
        val adapterM = ArrayAdapter(this, R.layout.item_list_dropdown, itemsMarc)
        edtMarca.setAdapter(adapterM)


        btnImagen.setOnClickListener{
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
        val btnButtonAtras = findViewById<ImageView>(R.id.btnAtras)
        btnButtonAtras.setOnClickListener{
            finish()
        }
        val intent = intent
        var nProducto = intent.getStringExtra("nProducto")
        var marca = intent.getStringExtra("marca")
        var categoria = intent.getStringExtra("categoria")
        var codigo = intent.getStringExtra("codigo")
        var precio = intent.getDoubleExtra("precio", 0.0)
        var imgProductoUrl = intent.getStringExtra("imgProducto")
        var stock = intent.getIntExtra("stock", 0)
        var descripcion = intent.getStringExtra("descripcion")

        // Llenar campos
        edtTitulo.setText(nProducto)
        edtMarca.setText(marca, false)
        edtCategoria.setText(categoria,false)
        edtPrecio.setText(precio.toString())
        edtStock.setText(stock.toString())
        edtDescripcion.setText(descripcion)
        textCodigo.text=codigo
        Picasso.get().load(imgProductoUrl).into(imgProducto)

        btnEditProductos.setOnClickListener{
            if (selectedImageUri != null) {
                uploadImageToStorage()
            } else {
                updateProductToFirestore(imgProductoUrl.toString())

            }
        }

    }

    private fun uploadImageToStorage() {
        val filename = UUID.randomUUID().toString()
        val ref = storage.reference.child("$filename")
        val uploadTask = selectedImageUri?.let { ref.putFile(it) }

        uploadTask?.addOnSuccessListener {
            ref.downloadUrl.addOnSuccessListener { uri ->
                updateProductToFirestore(uri.toString())
            }
        }?.addOnFailureListener {
            Toast.makeText(this, "Error al subir la imagen", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateProductToFirestore(imageUrl: String) {

        var nProducto:String = edtTitulo.text.toString()
        var marca:String = edtMarca.text.toString()
        var categoria :String= edtCategoria.text.toString()
        var codigo:String= textCodigo.text.toString()
        var precio :Double= edtPrecio.text.toString().toDouble()
        var descripcion:String = edtDescripcion.text.toString()
        var stock:Int = edtStock.text.toString().toInt()

        val data = HashMap<String, Any>()
        data["nProducto"]=nProducto
        data["marca"]=marca
        data["categoria"]=categoria
        data["precio"]=precio
        data["descripcion"]=descripcion
        data["stock"]=stock
        data["imgProducto"]=imageUrl

        db.collection("producto").document(codigo)
            .update(data)
            .addOnSuccessListener {
                Toast.makeText(this, "Actualizacion exitosa", Toast.LENGTH_LONG).show()
                val intent = Intent(this, ProductosListaAdminActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Algo salió mal", Toast.LENGTH_SHORT).show()
            }
    }
}