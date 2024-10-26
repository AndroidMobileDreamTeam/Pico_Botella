package com.example.pico_botella.webservice

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.pico_botella.utils.Constants.BASE_URL

object RetrofitClient {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}