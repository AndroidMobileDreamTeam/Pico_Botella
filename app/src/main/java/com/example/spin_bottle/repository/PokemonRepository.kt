package com.example.spin_bottle.repository

import com.example.spin_bottle.model.Pokemon
import com.example.spin_bottle.webservice.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getPokemons(): MutableList<Pokemon> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getPokemons()
                val pokemonList = response.pokemons
                pokemonList
            } catch (e: Exception) {

                e.printStackTrace()
                mutableListOf()
            }
        }
    }
}