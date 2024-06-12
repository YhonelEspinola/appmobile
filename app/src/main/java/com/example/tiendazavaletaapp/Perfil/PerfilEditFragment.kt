package com.example.tiendazavaletaapp.Perfil

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.tiendazavaletaapp.R

class PerfilEditFragment :Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_perfil,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editPassword: EditText = view.findViewById(R.id.edit_password)
        editPassword.setOnClickListener {
            val intent = Intent(activity, CambiarContraPerfilActivity::class.java)
            startActivity(intent)
        }

    }

    companion object{
        fun newInstance() : PerfilEditFragment = PerfilEditFragment()
    }
}