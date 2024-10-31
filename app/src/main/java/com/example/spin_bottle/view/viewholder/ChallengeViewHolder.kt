package com.example.spin_bottle.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.spin_bottle.model.Challenge
import com.example.spin_bottle.view.adapter.ChallengeActionListener
import com.example.spin_bottle_app.databinding.ItemChallengesBinding

import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.os.Handler
import android.os.Looper

class ChallengeViewHolder(
    private val binding: ItemChallengesBinding,
    private val listener: ChallengeActionListener
) :
    RecyclerView.ViewHolder(binding.root) {

    fun setItemChallenge(challenge: Challenge) {
        binding.challenge.text = challenge.description
    }

    fun setOnDeleteClickListener(challenge: Challenge) {
        binding.deleteChallengeButton.setOnClickListener {
            // Crear la animación de escala para que sea un poco más grande (1.3)
            val scaleUp = ScaleAnimation(
                1f, 1.3f,  // Escala inicial y final en X
                1f, 1.3f,  // Escala inicial y final en Y
                Animation.RELATIVE_TO_SELF, 0.5f,  // Punto de pivote X
                Animation.RELATIVE_TO_SELF, 0.5f   // Punto de pivote Y
            ).apply {
                duration = 85       // Duración de la animación en milisegundos
                fillAfter = true     // Mantener la animación final
            }

            // Escala de regreso a su tamaño original
            val scaleDown = ScaleAnimation(
                1.3f, 1f,
                1.3f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
            ).apply {
                duration = 85       // Duración del retorno
                fillAfter = true
            }

            // Iniciar ambas animaciones en secuencia
            binding.deleteChallengeButton.startAnimation(scaleUp)

            // Programar la animación de escala de regreso usando un Handler
            Handler(Looper.getMainLooper()).postDelayed({
                binding.deleteChallengeButton.startAnimation(scaleDown)
            }, 85)  // Espera a que la primera animación termine

            Handler(Looper.getMainLooper()).postDelayed({
                listener.onDeleteChallenge(challenge)
            }, 170)
        }
    }

    fun setOnEditClickListener(challenge: Challenge) {
        binding.editChallengeButton.setOnClickListener {
            // Crear la animación de escala para que sea un poco más grande (1.3)
            val scaleUp = ScaleAnimation(
                1f, 1.3f,  // Escala inicial y final en X
                1f, 1.3f,  // Escala inicial y final en Y
                Animation.RELATIVE_TO_SELF, 0.5f,  // Punto de pivote X
                Animation.RELATIVE_TO_SELF, 0.5f   // Punto de pivote Y
            ).apply {
                duration = 85       // Duración de la animación en milisegundos
                fillAfter = true     // Mantener la animación final
            }

            // Escala de regreso a su tamaño original
            val scaleDown = ScaleAnimation(
                1.3f, 1f,
                1.3f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
            ).apply {
                duration = 85       // Duración del retorno
                fillAfter = true
            }

            // Iniciar ambas animaciones en secuencia
            binding.editChallengeButton.startAnimation(scaleUp)

            // Programar la animación de escala de regreso usando un Handler
            Handler(Looper.getMainLooper()).postDelayed({
                binding.editChallengeButton.startAnimation(scaleDown)
            }, 85)  // Espera a que la primera animación termine
            
            Handler(Looper.getMainLooper()).postDelayed({
                listener.onEditChallenge(challenge)
            }, 170)
        }
    }
}