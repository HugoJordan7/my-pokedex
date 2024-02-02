package com.example.mypokedex.model

import com.google.gson.annotations.SerializedName

data class Type(
    @SerializedName("damage_relations") val relations: Relations
)

data class Relations(
    val doubleDamageFrom: List<PokemonTypeName>
)
