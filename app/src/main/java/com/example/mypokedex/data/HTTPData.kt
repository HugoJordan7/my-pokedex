package com.example.mypokedex.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HTTPData {

    private const val BASE_URL = "https://pokeapi.co/api/v2/"

    fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
