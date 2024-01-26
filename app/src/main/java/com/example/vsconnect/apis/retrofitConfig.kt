package com.example.vsconnect.apis

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class retrofitConfig {
    companion object{
        fun obterInstanciaRetrofit(url: String = "http://172.16.52.150:8099/"):Retrofit{
            return Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}