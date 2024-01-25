package com.example.mypokedex.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Pokemon(
    var form: PokemonForm,
    var specie: PokemonSpecie
): Serializable