package com.example.spin_bottle_app

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.spin_bottle_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        var keepSplashOnScreen = true

        installSplashScreen().setKeepOnScreenCondition { keepSplashOnScreen }

        Handler(Looper.getMainLooper()).postDelayed(
            { keepSplashOnScreen = false }, 5000)

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startTextViewAnimation()
        buttonStartAnimation()
        buttonStartClick()
    }

    private fun bottleAnimation() {
        val bottle = binding.bottle
        val animation = AnimationUtils.loadAnimation(this, R.anim.rotation)
        bottle.startAnimation(animation)
    }

    private fun startTextViewAnimation() {
        val textV = binding.textvPresioname
        val animation = AnimationUtils.loadAnimation(this, R.anim.grow)
        textV.startAnimation(animation)
    }

    private fun buttonStartAnimation() {
        val button = binding.btnStart
        val animation = AnimationUtils.loadAnimation(this, R.anim.grow)
        button.startAnimation(animation)
    }

    private fun fadeTextvAnimation() {
        val textV = binding.textvPresioname
        val animation = AnimationUtils.loadAnimation(this, R.anim.fade)
        textV.startAnimation(animation)
    }

    private fun fadeButtonAnimation() {
        val button = binding.btnStart
        val animation = AnimationUtils.loadAnimation(this, R.anim.fade)
        button.startAnimation(animation)
    }

    private fun buttonStartClick() {
        val button = binding.btnStart
        val textV = binding.textvPresioname
        button.setOnClickListener {
            button.clearAnimation()
            textV.clearAnimation()
            fadeTextvAnimation()
            textV.visibility = View.INVISIBLE
            fadeButtonAnimation()
            button.visibility = View.INVISIBLE
            bottleAnimation()
        }
    }
}
