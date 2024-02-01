package com.example.mypokedex.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PokemonForm(
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String,
    @SerializedName("types") var listTypes: List<PokemonType>
): Serializable

data class PokemonType(
    @SerializedName("slot") var slot: Int,
    @SerializedName("type") var name: PokemonTypeName
): Serializable

data class PokemonTypeName(
    @SerializedName("name") var name: String,
    @SerializedName("url") var url: String
): Serializable
