package com.example.spin_bottle.view.dialog

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LifecycleOwner
import com.bumptech.glide.Glide
import com.example.spin_bottle.viewmodel.ChallengesViewModel
import com.example.spin_bottle.viewmodel.PokemonsViewModel
import com.example.spin_bottle_app.databinding.ShowChallengeDialogBinding

class RandomChallengeDialog {

    companion object{
        fun show(
            context: Context,
            challengesViewModel: ChallengesViewModel,
            pokemonsViewModel: PokemonsViewModel
        ) {
            val inflater = LayoutInflater.from(context)
            val binding = ShowChallengeDialogBinding.inflate(inflater)

            val alertDialog = AlertDialog.Builder(context).create()
            alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            alertDialog.setCancelable(false)
            alertDialog.setView(binding.root)

            pokemonsViewModel.getPokemons()
            (context as? LifecycleOwner)?.let { lifecycleOwner ->
                pokemonsViewModel.getPokemons()
                pokemonsViewModel.pokemonsList.observe(lifecycleOwner) { lista ->
                    if (lista.size > 2) {
                        val pokemon = lista[2]
                        Glide.with(binding.root.context).load(pokemon.img).into(binding.ivPokemon)
                    }
                }
            }

            binding.btnCerrar.setOnClickListener {
                alertDialog.dismiss()
            }
            alertDialog.show()
        }
    }
}