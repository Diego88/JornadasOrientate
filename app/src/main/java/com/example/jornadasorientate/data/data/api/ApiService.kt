package com.example.jornadasorientate.data.data.api

import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.Retrofit

object ApiService {

    private const val BASE_URL = "https://reqres.in/api/"

    fun create(): ApiInterface {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }
}
