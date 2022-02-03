package com.example.demoapp.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.demoapp.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    lateinit var dataBinding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = ActivitySplashBinding.inflate(layoutInflater)
        val view = dataBinding.root
        setContentView(view)

        Handler().postDelayed(Runnable {
            // Start your app main activity
            val i = Intent(this@SplashActivity, UserList::class.java)
            startActivity(i)
            // close this activity
            finish()
        }, 3000)
    }
}