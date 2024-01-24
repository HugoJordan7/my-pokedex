package com.example.mypokedex.model

data class Pokemon(
    var id: Int,
    var name: String,
    var iconUrl: String,
    var primaryType: String,
    var secondType: String?
)
