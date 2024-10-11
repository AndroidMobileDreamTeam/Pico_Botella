package com.example.pico_botella

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.pico_botella.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    // Método para iniciar la animación del TextView
    private fun startTextViewAnimation() {
        val textv_presioname = binding.textvPresioname
        val animation = AnimationUtils.loadAnimation(this, R.anim.grow)
        textv_presioname.startAnimation(animation)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflar el layout usando View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Iniciar la animación
        startTextViewAnimation()
    }
}
