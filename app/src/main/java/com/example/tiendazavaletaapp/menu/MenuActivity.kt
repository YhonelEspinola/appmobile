package com.example.tiendazavaletaapp.menu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.tiendazavaletaapp.Perfil.PerfilFragment
import com.example.tiendazavaletaapp.R
import com.example.tiendazavaletaapp.carrito.CarritoFragment
import com.example.tiendazavaletaapp.recyclermain.MasVendidosFragment
import com.example.tiendazavaletaapp.recyclerwish.WishFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MenuActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val nav_view = findViewById<BottomNavigationView>(R.id.nav_view)
        nav_view.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.itemHome -> {
                    val fragment = MasVendidosFragment.newInstance()
                    openFragment(fragment)
                    true
                }

                R.id.itemCar -> {
                    val fragment = CarritoFragment.newInstance()
                    openFragment(fragment)
                    true
                }

                R.id.itemWish -> {
                    val fragment = WishFragment.newInstance()
                    openFragment(fragment)
                    true
                }

                R.id.itemProfile -> {
                    val fragment = PerfilFragment.newInstance()
                    openFragment(fragment)
                    true
                }

                else -> false
            }
        }

        nav_view.selectedItemId = R.id.itemHome
    }


    fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_menu, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}