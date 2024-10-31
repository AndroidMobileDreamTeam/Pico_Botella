package com.example.spin_bottle.model

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("id")
    val id: Int,

    @SerializedName("img")
    val img: String
)
