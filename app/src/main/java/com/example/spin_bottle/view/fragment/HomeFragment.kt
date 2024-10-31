package com.example.spin_bottle.view.fragment

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Intent
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
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.spin_bottle.view.dialog.RandomChallengeDialog
import com.example.spin_bottle.viewmodel.ChallengesViewModel
import com.example.spin_bottle.viewmodel.PokemonsViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.spin_bottle.viewmodel.AudioViewModel
import com.example.spin_bottle_app.R
import com.example.spin_bottle_app.databinding.HomeFragmentBinding
import kotlin.random.Random


class HomeFragment : Fragment() {

    private lateinit var binding: HomeFragmentBinding
    private lateinit var audioViewModel: AudioViewModel
    private var currentAngle = 0f
    private val challengeViewModel: ChallengesViewModel by viewModels()
    private val pokemonViewModel: PokemonsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(layoutInflater)

        audioViewModel = ViewModelProvider(requireActivity())[AudioViewModel::class.java]
        audioViewModel.initMediaPlayer(requireContext())

        // Observa el estado del audio
        audioViewModel.isAudioOn.observe(viewLifecycleOwner) { isAudioOn ->
            updateAudioState(isAudioOn)
        }

        toolbarButtons()
        animatedComponents()
        buttonStartClick()

        return binding.root
    }

    //Funciones del MediaPlayer
    private fun updateAudioState(isAudioOn: Boolean) {
        if (isAudioOn) {
            binding.customToolbar.btnVolume.setImageResource(R.drawable.icono_volume_on)
        } else {
            binding.customToolbar.btnVolume.setImageResource(R.drawable.icono_volume_off)
        }
    }

    private fun toggleAudio() {
        audioViewModel.toggleAudio()
    }

    private fun volumeButton(){
        val btnVolume = binding.customToolbar.btnVolume
        btnVolume.setOnClickListener {
            toggleAudio()
        }
    }

    override fun onResume() {
        super.onResume()
        if (audioViewModel.isAudioOn.value == true && !audioViewModel.mediaPlayer?.isPlaying!!) {
            audioViewModel.mediaPlayer?.start()
        }
    }

    override fun onPause() {
        super.onPause()
        if (audioViewModel.isAudioOn.value == true) {
            audioViewModel.mediaPlayer?.pause()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        audioViewModel.mediaPlayer?.release() // Libera el MediaPlayer al destruir la actividad
    }


    //Animación de la botella
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
                spinSound()
            }

            override fun onAnimationEnd(animation: Animator) {
                showChallenge()
                homeSound()
            }

            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })

        // Iniciar la animación
        animator.start()
        currentAngle = randomAngle
    }


    //Animaciones de los elementos
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


    //Sonidos
    private fun spinSound() {
        audioViewModel.spinSound(requireContext())
    }

    private fun homeSound() {
        audioViewModel.homeSound(requireContext())
    }


    //Mostrar reto aleatorio
    private fun showChallenge(){
        val showElementsCallback: () -> Unit = {
            showElements()
        }
        RandomChallengeDialog.show(requireContext(), challengeViewModel, pokemonViewModel, viewLifecycleOwner, showElementsCallback)
    }


    //Mostrar y ocultar elementos
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


    //Botón de inicio de giro
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


    //Botones de la barra de herramientas
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

    private fun instructionsButton(){
        val btnInstrucciones = binding.customToolbar.btnInstructions
        btnInstrucciones.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_instruccionesFragment)
        }
    }

    private fun challengesButton(){
        val btnChallenges = binding.customToolbar.btnChallenges
        btnChallenges.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_challengesFragment)
        }
    }

    private fun shareButton() {
        val btnShare = binding.customToolbar.btnShare
        btnShare.setOnClickListener {
            // AQUI VA EL CODIGO PARA COMPARTIR LA APP --> JUAN
        }
    }


    //Funciones de la barra de herramientas
    private fun toolbarButtons(){
        rateButton()
        volumeButton()
        instructionsButton()
        challengesButton()
        shareButton()
    }


    //Elementos animados
    private fun animatedComponents(){
        startTextViewAnimation()
        buttonStartAnimation()
    }
}

