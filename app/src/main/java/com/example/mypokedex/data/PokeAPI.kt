package com.example.mypokedex.data

import com.example.mypokedex.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface PokeAPI {

    @GET("pokemon/{id}")
    fun findPokemonById(@Path("id") id: Int): Call<Pokemon>

    @GET("pokemon/{name}")
    fun findPokemonByName(@Path("name") name: String): Call<Pokemon>

    @GET("pokemon-species/{id}")
    fun findPokemonSpecieById(@Path("id") id: Int): Call<PokemonSpecie>

    @GET("pokemon")
    fun findPokemonNamesList(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 1000
    ): Call<PokemonNamesList>

    @GET("type/{name}")
    fun findWeaknessesByTypeName(@Path("name") typeName: String): Call<Type>

    @GET
    fun findEvolutionChainByUrl(@Url url: String): Call<Evolutions>
}