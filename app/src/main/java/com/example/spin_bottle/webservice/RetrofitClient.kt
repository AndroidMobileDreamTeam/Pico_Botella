package com.example.spin_bottle.webservice

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.spin_bottle.utils.Constants.BASE_URL

object RetrofitClient {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}