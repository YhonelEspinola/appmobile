package com.example.tiendazavaletaapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.tiendazavaletaapp.login.LoginActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        val handler = Handler(Looper.getMainLooper())

        handler.postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 1500)


    }

    //    fun verBienvenida(){
//        object:CountDownTimer(2000,1000){
//            override fun onTick(millisUntilFinished: Long) {
//            }
//
//            override fun onFinish() {
//                val intent=Intent(this@MainActivity, LoginActivity::class.java)
//                startActivity(intent)
//                finishAffinity()
//            }
//
//        }.start()
//    }

}