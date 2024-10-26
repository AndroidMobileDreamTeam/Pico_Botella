package com.example.spin_bottle.view

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.spin_bottle_app.R

class MainActivity : AppCompatActivity() {
//import com.example.spin_bottle_app.databinding.ActivityMainBinding
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
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