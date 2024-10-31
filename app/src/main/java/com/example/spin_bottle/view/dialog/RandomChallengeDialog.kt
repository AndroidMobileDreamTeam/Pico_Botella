package com.example.spin_bottle.view.dialog

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LifecycleOwner
import com.bumptech.glide.Glide
import com.example.spin_bottle.viewmodel.ChallengesViewModel
import com.example.spin_bottle.viewmodel.PokemonsViewModel
import com.example.spin_bottle_app.databinding.ShowChallengeDialogBinding
import kotlin.random.Random

class RandomChallengeDialog {

    companion object{
        fun show(
            context: Context,
            challengesViewModel: ChallengesViewModel,
            pokemonsViewModel: PokemonsViewModel,
            lifecycleOwner: LifecycleOwner,
            showElementsCallback: () -> Unit,
            homeSoundCallback: () -> Unit,
        ) {
            val inflater = LayoutInflater.from(context)
            val binding = ShowChallengeDialogBinding.inflate(inflater)

            val alertDialog = AlertDialog.Builder(context).create()
            alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            alertDialog.setCancelable(false)
            alertDialog.setView(binding.root)

            challengesViewModel.getChallengesList()
            challengesViewModel.challengesList.observe(lifecycleOwner) { lista ->
                if (lista.isNotEmpty()) {
                    val randomIndex = Random.nextInt(lista.size)
                    val challenge = lista[randomIndex]
                    binding.tvChallenge.text = challenge.description
                }
            }

            pokemonsViewModel.getPokemons()
            pokemonsViewModel.pokemonsList.observe(lifecycleOwner) { lista ->
                if (lista.isNotEmpty()) {
                    val randomIndex = Random.nextInt(lista.size)
                    val pokemon = lista[randomIndex]
                    Glide.with(binding.root.context).load(pokemon.img).into(binding.ivPokemon)
                }
            }

            binding.btnCerrar.setOnClickListener {
                alertDialog.dismiss()
                showElementsCallback()
                homeSoundCallback()
            }
            alertDialog.show()
        }
    }
}