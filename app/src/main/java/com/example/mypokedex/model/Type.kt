package com.example.mypokedex.model

import com.google.gson.annotations.SerializedName

data class Type(
    @SerializedName("damage_relations") val relations: Relations?
)

data class Relations(
    @SerializedName("double_damage_from") val doubleDamageFrom: List<PokemonTypeName>?,
    @SerializedName("half_damage_from") val halfDamageFrom: List<PokemonTypeName>?,
    @SerializedName("no_damage_from") val noDamageFrom: List<PokemonTypeName>?
)
