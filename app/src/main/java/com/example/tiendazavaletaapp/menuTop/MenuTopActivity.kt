package com.example.tiendazavaletaapp.menuTop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.tiendazavaletaapp.R
import com.example.tiendazavaletaapp.pagoEntrega.PagoEntregraFragment
import com.example.tiendazavaletaapp.recojoTienda.RecojoTiendaFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MenuTopActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_top)

        val nav_view2 =findViewById<BottomNavigationView>(R.id.nav_view2)
        nav_view2.setOnItemSelectedListener {
            when(it.itemId){
                R.id.itemPagoEntrega -> {
                    val fragment =PagoEntregraFragment.pagoentregaInstance()
                    openFregment(fragment)
                    true
                }
                R.id.itemRecojoTienda -> {
                    val fragment = RecojoTiendaFragment.recojoInstance()
                    openFregment(fragment)
                    true
                }
                else -> false
            }
        }

        nav_view2.selectedItemId = R.id.itemPagoEntrega
    }

    fun openFregment(fragment : Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_menu2, fragment)
        transaction.commit()
    }
}