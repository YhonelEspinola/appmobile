package com.example.tiendazavaletaapp.menu

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.tiendazavaletaapp.Perfil.PerfilEditFragment
import com.example.tiendazavaletaapp.Perfil.PerfilFragment
import com.example.tiendazavaletaapp.R
import com.example.tiendazavaletaapp.buscar.BuscarFragment
import com.example.tiendazavaletaapp.carrito.CarritoFragment
import com.example.tiendazavaletaapp.pedidosUser.PedidosUserFragment
import com.example.tiendazavaletaapp.home.HomeFragment
import com.example.tiendazavaletaapp.login.LoginActivity
import com.example.tiendazavaletaapp.menuTop.MenuTopActivity
/*import com.example.tiendazavaletaapp.recyclerwish.WishFragment*/
import com.example.tiendazavaletaapp.vermas.VerMasFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class MenuActivity: AppCompatActivity() {

    private var firebaseAuth: FirebaseAuth?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        firebaseAuth = FirebaseAuth.getInstance()

        var valor = intent.getIntExtra("valor",0)


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
                    comprobarSesionC()
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
                    PerfilEditFragment::class.java,
                    PedidosUserFragment::class.java,
                    VerMasFragment::class.java,
                    CarritoFragment::class.java,
                    PerfilFragment::class.java)) {
                nav_view.selectedItemId = getFragmentMenuItemId(fragment)
            }
        }

        if(valor==1){
            nav_view.selectedItemId = R.id.itemCar
        }else{
            nav_view.selectedItemId = R.id.itemHome
        }



    }
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
            finish()
        }

    }
    override fun onResume() {
        super.onResume()
        val nav_view = findViewById<BottomNavigationView>(R.id.nav_view)
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_menu)
        if (fragment != null) {
            nav_view.selectedItemId = getFragmentMenuItemId(fragment)
        }
    }

    fun openFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val currentFragment = fragmentManager.findFragmentById(R.id.fragment_menu)

        if (currentFragment != null && currentFragment::class.java == fragment::class.java) {
            return // El fragmento ya está mostrado, no hagas nada
        }

        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_menu, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    private fun getFragmentMenuItemId(fragment: Fragment): Int {
        return when (fragment) {
            is HomeFragment -> R.id.itemHome
            is BuscarFragment -> R.id.itemSearch
            is CarritoFragment -> R.id.itemCar
            is PerfilFragment, is PerfilEditFragment, is PedidosUserFragment, is VerMasFragment -> R.id.itemProfile
            else -> R.id.itemHome // Default case
        }
    }

    private fun comprobarSesionC() {
        /*Si el usuario no ha iniciado sesion*/
        if (firebaseAuth!!.currentUser==null){
            startActivity(Intent(this, LoginActivity::class.java))
            Toast.makeText(this,"Usuario no logeado", Toast.LENGTH_SHORT).show()

        }else{
            val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_menu)

            when(currentFragment){
                is PerfilFragment -> openFragment(PerfilFragment.newInstance())
                is PerfilEditFragment -> openFragment(PerfilEditFragment.newInstance())
                is PedidosUserFragment -> openFragment(PedidosUserFragment.newInstance())
                is VerMasFragment -> openFragment(VerMasFragment.newInstance())
                else -> openFragment(PerfilFragment.newInstance())
            }
        }
    }
}