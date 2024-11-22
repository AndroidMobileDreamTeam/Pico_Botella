package com.example.spin_bottle.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spin_bottle.model.Pokemon
import com.example.spin_bottle.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonsViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    private val _pokemonsList = MutableLiveData<MutableList<Pokemon>>()
    val pokemonsList: LiveData<MutableList<Pokemon>> get() = _pokemonsList

    fun getPokemons() {
        viewModelScope.launch {
            try {
                _pokemonsList.value = pokemonRepository.getPokemons()

            } catch (_: Exception) {
            }
        }
    }
}