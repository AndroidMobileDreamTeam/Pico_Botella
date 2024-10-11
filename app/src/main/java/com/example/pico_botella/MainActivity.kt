package com.example.pico_botella

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.pico_botella.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflar el layout usando View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Iniciar la animación
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
