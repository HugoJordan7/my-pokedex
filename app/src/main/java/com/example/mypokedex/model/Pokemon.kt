package com.example.mypokedex.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Pokemon(
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String,
    @SerializedName("types") val types: List<PokemonType>,
    @SerializedName("stats") val stats: List<PokemonStatus>,
    @SerializedName("height") val height: Int,
    @SerializedName("weight") val weight: Int,
    var specie: PokemonSpecie? = null,
    var iconUrl: String? = null
): Serializable