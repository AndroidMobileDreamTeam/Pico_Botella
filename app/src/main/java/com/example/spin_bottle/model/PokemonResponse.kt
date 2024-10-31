package com.example.spin_bottle.model

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @SerializedName("pokemon")
    val pokemons: MutableList<Pokemon>
)
