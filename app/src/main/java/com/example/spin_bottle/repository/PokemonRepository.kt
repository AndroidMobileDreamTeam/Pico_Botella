package com.example.spin_bottle.repository

import android.content.Context
import com.example.spin_bottle.model.Pokemon
import com.example.spin_bottle.webservice.ApiService
import com.example.spin_bottle.webservice.ApiUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonRepository(val context: Context) {
    private var apiService: ApiService = ApiUtils.getApiService()

    suspend fun getPokemons(): MutableList<Pokemon> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getPokemons()
                response
            } catch (e: Exception) {

                e.printStackTrace()
                mutableListOf()
            }
        }
    }
}