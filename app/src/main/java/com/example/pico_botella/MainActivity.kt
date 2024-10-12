package com.example.pico_botella

import android.animation.Animator
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.pico_botella.databinding.ActivityMainBinding
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var currentAngle = 0f

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

        // Generar un ángulo aleatorio
        val randomAngle = currentAngle + Random.nextInt(2000, 3600)



        // Crear la animación de rotación
        val animator = ObjectAnimator.ofFloat(bottle, "rotation", currentAngle, randomAngle)

        // Establecer la duración de la animación
        animator.duration = 5000 // 3 segundos, por ejemplo

        // Añadir un interpolador para hacer la animación más suave
        animator.interpolator = AccelerateDecelerateInterpolator()

        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
                // Opcional: código para cuando la animación empieza
            }

            override fun onAnimationEnd(animation: Animator) {
                // Mostrar los botones de nuevo
                showElements()
            }

            override fun onAnimationCancel(animation: Animator) {
                // Opcional: código para manejar cancelaciones
            }

            override fun onAnimationRepeat(animation: Animator) {
                // Opcional: código para repeticiones de la animación
            }
        })

        // Iniciar la animación
        animator.start()
        currentAngle = randomAngle

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

    private fun showElements(){
        val button = binding.btnStart
        val textV = binding.textvPresioname
        button.visibility = View.VISIBLE
        textV.visibility = View.VISIBLE
    }

    private fun hideElements(){
        val button = binding.btnStart
        val textV = binding.textvPresioname
        fadeTextvAnimation()
        button.visibility = View.INVISIBLE
        fadeButtonAnimation()
        textV.visibility = View.INVISIBLE
    }

    private fun buttonStartClick() {
        val button = binding.btnStart
        val textV = binding.textvPresioname
        button.setOnClickListener {
            button.clearAnimation()
            textV.clearAnimation()
            hideElements()
            bottleAnimation()
        }
    }
}
