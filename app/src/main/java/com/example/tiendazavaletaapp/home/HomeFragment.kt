package com.example.tiendazavaletaapp.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.R

class HomeFragment: Fragment(), OnCategoriaClickListener {
    private lateinit var recyclerCategoria: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerCategoria = view.findViewById(R.id.recyclerCategorias)

        val listCategoria = listOf<Categoria>(
            Categoria("Inicio"),
            Categoria("Utiles de oficina"),
            Categoria("Papeleria"),
            Categoria("Arte y Diseño"),
            Categoria("Mochilas"),
            Categoria("Cuadernos, Libretas"),
            Categoria("Textos Escolares"),
        )
        val adapterC = CategoriaAdapter(listCategoria,this)
        recyclerCategoria.adapter=adapterC
        recyclerCategoria.layoutManager=
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)

        if (listCategoria.isNotEmpty()) {
            onCategoriaClick(listCategoria[0]) // Llama al método para cargar el fragmento del primer elemento
        }
    }

    override fun onCategoriaClick(categoria: Categoria) {
        val fragment = when (categoria.categoria) {
            "Inicio" -> HomeSubfragment()
            "Utiles de oficina", "Papeleria",
            "Arte y Diseño", "Mochilas",
            "Cuadernos, Libretas",
            "Textos Escolares" -> ListCategoriaSubfragment()
            else -> HomeSubfragment()
        }
        val bundle = Bundle()
        bundle.putString("catselect", categoria.categoria)
        fragment.arguments = bundle

        childFragmentManager.beginTransaction()
            .replace(R.id.subfragment_home, fragment)
            .commit()
    }

    companion object{
        fun newInstance() : HomeFragment = HomeFragment()
    }
}