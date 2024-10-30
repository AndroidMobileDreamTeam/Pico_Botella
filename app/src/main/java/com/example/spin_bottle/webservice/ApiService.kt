package com.example.spin_bottle.webservice

import com.example.spin_bottle.model.Pokemon
import com.example.spin_bottle.utils.Constants.END_POINT
import retrofit2.http.GET

interface ApiService {
     @GET(END_POINT)
     suspend fun getPokemons(): MutableList<Pokemon>
}