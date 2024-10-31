package com.example.spin_bottle.webservice

class ApiUtils {
    companion object {
        fun getApiService(): ApiService {
            return RetrofitClient.getRetrofit().create(ApiService::class.java)
        }
    }
}