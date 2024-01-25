package com.example.mypokedex.util

import android.content.Context
import android.graphics.Color
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.example.mypokedex.R
import com.example.mypokedex.model.Pokemon

object ProjectResources {

    fun getListOfPokemonId(firstId: Int, lastId: Int): List<Int>{
        var list: MutableList<Int> = mutableListOf()
        list.addAll(firstId .. lastId)
        return list
    }

    fun setImageByPokemonType(type: String, imageType: ImageView) {
        when (type.toLowerCase()) {
            "normal" -> imageType.setImageResource(R.drawable.normal_type)
            "fighting" -> imageType.setImageResource(R.drawable.fight_type)
            "flying" -> imageType.setImageResource(R.drawable.flying_type)
            "poison" -> imageType.setImageResource(R.drawable.poison_type)
            "ground" -> imageType.setImageResource(R.drawable.ground_type)
            "rock" -> imageType.setImageResource(R.drawable.rock_type)
            "bug" -> imageType.setImageResource(R.drawable.bug_type)
            "ghost" -> imageType.setImageResource(R.drawable.ghost_type)
            "steel" -> imageType.setImageResource(R.drawable.steel_type)
            "fire" -> imageType.setImageResource(R.drawable.fire_type)
            "water" -> imageType.setImageResource(R.drawable.water_type)
            "grass" -> imageType.setImageResource(R.drawable.grass_type)
            "electric" -> imageType.setImageResource(R.drawable.electric_type)
            "psychic" -> imageType.setImageResource(R.drawable.psychic_type)
            "ice" -> imageType.setImageResource(R.drawable.ice_type)
            "dragon" -> imageType.setImageResource(R.drawable.dragon_type)
            "dark" -> imageType.setImageResource(R.drawable.dark_type)
            "fairy" -> imageType.setImageResource(R.drawable.fairy_type)
            else -> imageType.setImageResource(R.drawable.normal_type)
        }
    }

    fun getIntArrayColors(pokemonColor: String, context: Context): IntArray {
        val colorMap = mapOf(
            "black" to arrayOf(R.color.black_transparent_1, R.color.black_transparent_2, R.color.black_transparent_3),
            "blue" to arrayOf(R.color.blue_transparent_1, R.color.blue_transparent_2, R.color.blue_transparent_3),
            "green" to arrayOf(R.color.green_transparent_1, R.color.green_transparent_2, R.color.green_transparent_3),
            "pink" to arrayOf(R.color.pink_transparent_1, R.color.pink_transparent_2, R.color.pink_transparent_3),
            "purple" to arrayOf(R.color.purple_transparent_1, R.color.purple_transparent_2, R.color.purple_transparent_3),
            "red" to arrayOf(R.color.red_transparent_1, R.color.red_transparent_2, R.color.red_transparent_3),
            "white" to arrayOf(R.color.write_transparent_1, R.color.write_transparent_2, R.color.write_transparent_3),
            "yellow" to arrayOf(R.color.yellow_transparent_1, R.color.yellow_transparent_2, R.color.yellow_transparent_3),
        )
        var arrayId = colorMap[pokemonColor.toLowerCase()] ?: colorMap["black"] as Array<Int>
        return arrayId.map { ContextCompat.getColor(context,it) }.toIntArray()
    }

    fun getMutableListPokemonTest() = mutableListOf<Pokemon>(
        Pokemon(
            id = 2,
            name = "ivysaur",
            iconUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/2.png",
            primaryType = "grass",
            secondType = "poison",
            color = "green"
        ),
        Pokemon(
            id = 150,
            name = "charizard",
            iconUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/6.png",
            primaryType = "fire",
            secondType = "flying",
            color = "red"
        ),
        Pokemon(
            id = 93,
            name = "haunter",
            iconUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/93.png",
            primaryType = "ghost",
            secondType = "poison",
            color = "purple"
        ),
        Pokemon(
            id = 9999,
            name = "blastoise",
            iconUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/9.png",
            primaryType = "water",
            secondType = "normal",
            color = "blue"
        ),
        Pokemon(
            id = 2,
            name = "ivysaur",
            iconUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/2.png",
            primaryType = "grass",
            secondType = "poison",
            color = "yellow"
        ),
        Pokemon(
            id = 150,
            name = "charizard",
            iconUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/6.png",
            primaryType = "fire",
            secondType = "flying",
            color = "white"
        ),
        Pokemon(
            id = 9999,
            name = "blastoise",
            iconUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/9.png",
            primaryType = "water",
            color = "pink"
        ),
        Pokemon(
            id = 9999,
            name = "blastoise",
            iconUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/9.png",
            primaryType = "water",
            color = "black"
        )
    )

}