package com.example.mypokedex.model

data class Pokemon(
    var id: Int,
    var name: String,
    var iconUrl: String,
    var listType: List<String>
)
