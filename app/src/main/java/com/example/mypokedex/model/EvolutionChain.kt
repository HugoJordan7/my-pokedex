package com.example.mypokedex.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Evolutions(
    @SerializedName("chain") val chain: Chain
): Serializable

data class Chain(
    @SerializedName("evolves_to") val evolvesTo: List<Chain>,
    @SerializedName("species") val species: Specie
): Serializable

data class Specie(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
): Serializable
