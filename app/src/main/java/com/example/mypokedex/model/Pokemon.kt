package com.example.mypokedex.model

import com.example.mypokedex.util.ProjectResources
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Pokemon(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("types") val types: List<PokemonType>,
    @SerializedName("stats") val stats: List<PokemonStatus>,
    @SerializedName("height") val height: Int,
    @SerializedName("weight") val weight: Int,
    var specie: PokemonSpecie? = null
): Serializable