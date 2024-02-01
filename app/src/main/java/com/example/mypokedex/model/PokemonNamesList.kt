package com.example.mypokedex.model

import com.google.gson.annotations.SerializedName


data class PokemonNamesList(
    @SerializedName("results") val results: List<PokemonName>
)

data class PokemonName(
    @SerializedName("name") var name: String,
    @SerializedName("url") var url: String
)
