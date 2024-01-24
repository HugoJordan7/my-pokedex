package com.example.mypokedex.model

import java.io.Serializable

data class Pokemon(
    var id: Int,
    var name: String,
    var iconUrl: String,
    var primaryType: String,
    var secondType: String?
): Serializable
