package com.example.mypokedex.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HTTPData {

    private const val BASE_URL = "https://pokeapi.co/api/v2/"

    const val POKEMON_FORM_BASE_URL = "https://pokeapi.co/api/v2/pokemon-form/"
    const val POKEMON_SPECIE_BASE_URL = "https://pokeapi.co/api/v2/pokemon-species/"

    fun retrofit() = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

}