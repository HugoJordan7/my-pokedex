package com.example.mypokedex.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PokemonSpecie(
    @SerializedName("color") var color: PokemonColor,
    @SerializedName("habitat") val habitat: Habitat,
    @SerializedName("flavor_text_entries") val descriptionList: List<Description>,
    @SerializedName("evolution_chain") val evolutionChain: EvolutionChain
): Serializable

data class PokemonColor(
    @SerializedName("name") var name: String,
    @SerializedName("url") var url: String,
): Serializable

data class Habitat(
    @SerializedName("name") val name: String
): Serializable

data class Description(
    @SerializedName("flavor_text") val text: String,
    @SerializedName("language") val language: Language,
    @SerializedName("version") val version: Version
): Serializable

data class Language(
    @SerializedName("name") var name: String,
    @SerializedName("url") var url: String,
): Serializable

data class Version(
    @SerializedName("name") var name: String,
    @SerializedName("url") var url: String,
): Serializable

data class EvolutionChain(
    @SerializedName("url") val url: String
): Serializable