package com.example.mypokedex.model

import java.io.Serializable

data class Pokemon(
    var form: PokemonForm,
    var specie: PokemonSpecie
): Serializable