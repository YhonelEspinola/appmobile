package com.example.tiendazavaletaapp.menu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.tiendazavaletaapp.Perfil.PerfilEditFragment
import com.example.tiendazavaletaapp.Perfil.PerfilFragment
import com.example.tiendazavaletaapp.R
import com.example.tiendazavaletaapp.buscar.BuscarFragment
import com.example.tiendazavaletaapp.carrito.CarritoFragment
import com.example.tiendazavaletaapp.pedidosUser.PedidosUserFragment
import com.example.tiendazavaletaapp.home.HomeFragment
import com.example.tiendazavaletaapp.vermas.VerMasFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MenuActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val nav_view = findViewById<BottomNavigationView>(R.id.nav_view)
        nav_view.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.itemHome -> {
                    val fragment = HomeFragment.newInstance()
                    openFragment(fragment)
                    true
                }

                R.id.itemSearch -> {
                    val fragment = BuscarFragment.newInstance()
                    openFragment(fragment)
                    true
                }

                R.id.itemCar -> {
                    val fragment = CarritoFragment.newInstance()
                    openFragment(fragment)
                    true
                }

                R.id.itemProfile -> {
                    val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_menu)

                    when(currentFragment){
                        is PerfilFragment -> openFragment(PerfilFragment.newInstance())
                        is PerfilEditFragment -> openFragment(PerfilEditFragment.newInstance())
                        is PedidosUserFragment -> openFragment(PedidosUserFragment.newInstance())
                        is VerMasFragment -> openFragment(VerMasFragment.newInstance())
                        else -> openFragment(PerfilFragment.newInstance())
                    }
                    true
                }

                else -> false
            }
        }
        supportFragmentManager.addOnBackStackChangedListener {
            val fragment = supportFragmentManager.findFragmentById(R.id.fragment_menu)
            if (fragment != null && fragment::class.java in setOf(
                    HomeFragment::class.java,
                    BuscarFragment::class.java,
                    CarritoFragment::class.java,
                    PedidosUserFragment::class.java,
                    VerMasFragment::class.java,
                    PerfilEditFragment::class.java,
                    PerfilFragment::class.java)) {
                nav_view.selectedItemId = getFragmentMenuItemId(fragment)
            }
        }
        nav_view.selectedItemId = R.id.itemHome
    }
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
            finish()
        }
    }

    fun openFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val currentFragment = fragmentManager.findFragmentById(R.id.fragment_menu)

        if (currentFragment != null && currentFragment::class.java == fragment::class.java) {
            return
        }

        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_menu, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    private fun getFragmentMenuItemId(fragment: Fragment): Int {
        return when (fragment) {
            is HomeFragment-> R.id.itemHome
            is BuscarFragment -> R.id.itemSearch
            is CarritoFragment -> R.id.itemCar
            is PerfilFragment, is PerfilEditFragment, is PedidosUserFragment, is VerMasFragment -> R.id.itemProfile
            else -> R.id.itemHome
        }
    }
}