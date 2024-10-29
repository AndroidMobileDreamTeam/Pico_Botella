package com.example.spin_bottle.view.fragment

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.spin_bottle_app.R
import com.example.spin_bottle_app.databinding.HomeFragmentBinding
import kotlin.random.Random


class HomeFragment : Fragment() {

    private lateinit var binding: HomeFragmentBinding
    private var currentAngle = 0f
    private var isAudioOn = true
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = HomeFragmentBinding.inflate(layoutInflater)

        mediaPlayer()
        rateButton()
        volumeButton()
        instructionsButton()
        challengesButton()
        shareButton()
        startTextViewAnimation()
        buttonStartAnimation()
        buttonStartClick()

        return binding.root
    }

    private fun toggleAudio(btnVolume: ImageButton) {
        if (isAudioOn) {
            mediaPlayer.pause()
            btnVolume.setImageResource(R.drawable.icono_volume_off)
        } else {
            mediaPlayer.start()
            btnVolume.setImageResource(R.drawable.icono_volume_on)
        }
        isAudioOn = !isAudioOn
    }


    private fun bottleAnimation() {
        val bottle = binding.bottle

        // Generar un ángulo aleatorio
        val randomAngle = currentAngle + Random.nextInt(2000, 3600)



        // Crear la animación de rotación
        val animator = ObjectAnimator.ofFloat(bottle, "rotation", currentAngle, randomAngle)

        // Establecer la duración de la animación
        animator.duration = 4000 // 3 segundos, por ejemplo

        // Añadir un interpolador para hacer la animación más suave
        animator.interpolator = AccelerateDecelerateInterpolator()

        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
            }

            override fun onAnimationEnd(animation: Animator) {
                showElements()

            }

            override fun onAnimationCancel(animation: Animator) {
            }

            override fun onAnimationRepeat(animation: Animator) {
            }
        })

        // Iniciar la animación
        animator.start()
        currentAngle = randomAngle
    }

    private fun startTextViewAnimation() {
        val textV = binding.textvPressMe
        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.grow)
        textV.startAnimation(animation)
    }

    private fun buttonStartAnimation() {
        val button = binding.btnStart
        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.grow)
        button.startAnimation(animation)
    }

    private fun fadeTextvAnimation() {
        val textV = binding.textvPressMe
        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade)
        textV.startAnimation(animation)
    }

    private fun fadeButtonAnimation() {
        val button = binding.btnStart
        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade)
        button.startAnimation(animation)
    }

    private fun counterTextAnimation() {
        val txtV = binding.counterText

        val timer = object : CountDownTimer(4000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = millisUntilFinished / 1000
                txtV.text = secondsRemaining.toString()
            }

            override fun onFinish() {
                txtV.text = ""
            }
        }
        timer.start()
    }

    private fun showElements(){
        val button = binding.btnStart
        val textV = binding.textvPressMe
        val toolbar = binding.customToolbar.toolbar
        button.visibility = View.VISIBLE
        textV.visibility = View.VISIBLE
        toolbar.visibility = View.VISIBLE
        startTextViewAnimation()
        buttonStartAnimation()
    }

    private fun hideElements(){
        val button = binding.btnStart
        val textV = binding.textvPressMe
        val toolbar = binding.customToolbar.toolbar
        fadeTextvAnimation()
        button.visibility = View.INVISIBLE
        fadeButtonAnimation()
        textV.visibility = View.INVISIBLE
        toolbar.visibility = View.INVISIBLE
    }

    private fun buttonStartClick() {
        val button = binding.btnStart
        val textV = binding.textvPressMe

        button.setOnClickListener {
            button.clearAnimation()
            textV.clearAnimation()
            hideElements()
            counterTextAnimation()
            bottleAnimation()
        }
    }

    private fun mediaPlayer(){
        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.background_music)
        mediaPlayer.isLooping = true
        mediaPlayer.start()
    }

    private fun rateButton(){
        val btnRate = binding.root.findViewById<ImageButton>(R.id.btn_rate)
        btnRate.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://play.google.com/store/apps/details?id=com.nequi.MobileApp&hl=es_419&gl=es")
                setPackage("com.android.vending")
            }
            startActivity(intent)
        }
    }

    private fun volumeButton(){
        val btnVolume = binding.root.findViewById<ImageButton>(R.id.btn_volume)
        btnVolume.setOnClickListener {
            toggleAudio(btnVolume)
        }
    }

    private fun instructionsButton(){
        val btnInstrucciones = binding.root.findViewById<ImageButton>(R.id.btn_instructions)
        btnInstrucciones.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_instruccionesFragment)
        }
    }

    private fun challengesButton(){
        val btnChallenges = binding.root.findViewById<ImageButton>(R.id.btn_challenges)
        btnChallenges.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_challengesFragment)
        }
    }

    private fun shareButton() {
        val btnShare = binding.root.findViewById<ImageButton>(R.id.btn_share)
        btnShare.setOnClickListener {
            // AQUI VA EL CODIGO PARA COMPARTIR LA APP --> JUAN
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mediaPlayer.stop()
    }
}

