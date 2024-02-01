package com.example.mypokedex.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PokemonSpecie(
    @SerializedName("color") var color: PokemonColor,
    @SerializedName("habitat") val habitat: Habitat,
    @SerializedName("flavor_text_entries") val descriptionList: List<Description>
): Serializable

data class PokemonColor(
    @SerializedName("name") var name: String,
    @SerializedName("url") var url: String,
): Serializable

data class Habitat(
    @SerializedName("name") val name: String
): Serializable

data class Description(
    @SerializedName("flavor_text") val text: String
): Serializable
