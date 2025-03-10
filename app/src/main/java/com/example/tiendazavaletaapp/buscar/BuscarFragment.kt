package com.example.tiendazavaletaapp.buscar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.ProductosListaAdmin.ProductosListaAdmin
import com.example.tiendazavaletaapp.R
import com.example.tiendazavaletaapp.home.BuscarAdapter
import com.example.tiendazavaletaapp.home.ListCategoriaViewModel
import com.google.firebase.firestore.FirebaseFirestore

class BuscarFragment : Fragment() {
    private lateinit var adapterB: BuscarAdapter
    private lateinit var viewModel: ListCategoriaViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_buscar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[ListCategoriaViewModel::class.java]
        val recyclerSearch = view.findViewById<RecyclerView>(R.id.recyclerSearch)
        val searchView = view.findViewById<SearchView>(R.id.search)
        adapterB = BuscarAdapter()
        recyclerSearch.adapter = adapterB
        recyclerSearch.layoutManager = GridLayoutManager(context, 2)

        viewModel.listProductos("")
        viewModel.listProductosLCat.observe(viewLifecycleOwner) {

            if (it.isNotEmpty()) {
                adapterB.setDatos(it)
            }

        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { adapterB.filter(it) }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { adapterB.filter(it) }
                return false
            }
        })
    }

    companion object {
        fun newInstance(): BuscarFragment = BuscarFragment()
    }
}