package com.example.tiendazavaletaapp.recyclerwish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendazavaletaapp.R

class WishFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_wish,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val listWish = listOf<Deseado>(
            Deseado("Marca 1","Deseado 1","Cateogria",10.00,R.drawable.img),
            Deseado("Marca 2","Deseado 2","Cateogria",20.00,R.drawable.img),
            Deseado("Marca 2","Deseado 3","Cateogria",5.00,R.drawable.img),
            Deseado("Marca 3","Deseado 4","Cateogria",25.00,R.drawable.img),
            Deseado("Marca 1","Deseado 5","Cateogria",12.30,R.drawable.img),
        )

        val recyclerWish =view.findViewById<RecyclerView>(R.id.recyclerWish)

        val adapterW = DeseadosAdapter(listWish)
        recyclerWish.adapter= adapterW
        recyclerWish.layoutManager= GridLayoutManager(context,2)
    }

    companion object{
        fun newInstance() : WishFragment = WishFragment()
    }


}