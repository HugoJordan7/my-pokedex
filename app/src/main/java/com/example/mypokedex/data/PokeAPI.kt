package com.example.mypokedex.data

import com.example.mypokedex.model.Pokemon
import com.example.mypokedex.model.PokemonForm
import com.example.mypokedex.model.PokemonFormItem
import com.example.mypokedex.model.PokemonSpecieItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeAPI {

    //https://pokeapi.co/api/v2/pokemon-form?offset=0&limit=1473
    @GET("pokemon-form")
    fun findAllPokemonInAPI(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 1473
    ): Call<List<PokemonForm>>

    @GET("pokemon-form/{id}")
    fun findPokemonForm(id: Int): PokemonFormItem

    @GET("pokemon-species/{id}")
    fun findPokemonSpecie( @Path("id") id: Int): PokemonSpecieItem
}