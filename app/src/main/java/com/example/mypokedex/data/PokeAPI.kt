package com.example.mypokedex.data

import com.example.mypokedex.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeAPI {

    @GET("pokemon-form/{id}")
    fun findPokemonForm(@Path("id") id: Int): Call<PokemonForm>

    @GET("pokemon-species/{id}")
    fun findPokemonSpecie(@Path("id") id: Int): Call<PokemonSpecie>
}