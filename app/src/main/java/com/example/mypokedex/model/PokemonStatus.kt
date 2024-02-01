package com.example.mypokedex.model

import com.google.gson.annotations.SerializedName

data class PokemonStatus(
    @SerializedName("base_stat") val baseStat: Int,
    @SerializedName("effort") val effort: Int,
    @SerializedName("stat") val stat: Stat
)

data class Stat(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)