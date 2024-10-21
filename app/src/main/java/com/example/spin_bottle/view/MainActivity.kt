package com.example.spin_bottle.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.spin_bottle_app.R

class MainActivity : AppCompatActivity() {
//import com.example.spin_bottle_app.databinding.ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        var keepSplashOnScreen = true

        installSplashScreen().setKeepOnScreenCondition { keepSplashOnScreen }

        Handler(Looper.getMainLooper()).postDelayed(
            { keepSplashOnScreen = false }, 5000)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)

    }
}