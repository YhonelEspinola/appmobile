package com.example.tiendazavaletaapp.registroCliente

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tiendazavaletaapp.databinding.ActivityRegistroClienteBinding

class RegistroClienteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroClienteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroClienteBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}