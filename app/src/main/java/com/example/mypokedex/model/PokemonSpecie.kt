package com.example.mypokedex.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PokemonSpecie(
    @SerializedName("color") var color: PokemonColor
): Serializable

data class PokemonColor(
    @SerializedName("name") var name: String,
    @SerializedName("url") var url: String
): Serializable
