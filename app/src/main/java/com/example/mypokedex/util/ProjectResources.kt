package com.example.mypokedex.util

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mypokedex.R
import com.example.mypokedex.contract.HomeContract
import com.example.mypokedex.model.Pokemon
import com.example.mypokedex.model.PokemonSpecie
import retrofit2.Call
import retrofit2.Response

object ProjectResources {

    fun getPokeImgUrlById(id: Int): String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/$id.png"

    fun getListOfRangeId(firstId: Int, lastId: Int): List<Int>{
        var list: MutableList<Int> = mutableListOf()
        list.addAll(firstId .. lastId)
        return list
    }

    @DrawableRes
    fun getImageByPokemonType(type: String): Int {
        return when (type.toLowerCase()) {
            "normal" -> R.drawable.normal_type
            "fighting" -> R.drawable.fight_type
            "flying" -> R.drawable.flying_type
            "poison" -> R.drawable.poison_type
            "ground" -> R.drawable.ground_type
            "rock" -> R.drawable.rock_type
            "bug" -> R.drawable.bug_type
            "ghost" -> R.drawable.ghost_type
            "steel" -> R.drawable.steel_type
            "fire" -> R.drawable.fire_type
            "water" -> R.drawable.water_type
            "grass" -> R.drawable.grass_type
            "electric" -> R.drawable.electric_type
            "psychic" -> R.drawable.psychic_type
            "ice" -> R.drawable.ice_type
            "dragon" -> R.drawable.dragon_type
            "dark" -> R.drawable.dark_type
            "fairy" -> R.drawable.fairy_type
            else -> R.drawable.normal_type
        }
    }

    fun getIntArrayColors(pokemonColor: String, context: Context): IntArray {
        val colorMap = mapOf(
            "black" to arrayOf(R.color.black_transparent_3, R.color.black_transparent_2, R.color.black_transparent_1),
            "blue" to arrayOf(R.color.blue_transparent_3, R.color.blue_transparent_2, R.color.blue_transparent_1),
            "green" to arrayOf(R.color.green_transparent_3, R.color.green_transparent_2, R.color.green_transparent_1),
            "pink" to arrayOf(R.color.pink_transparent_3, R.color.pink_transparent_2, R.color.pink_transparent_1),
            "purple" to arrayOf(R.color.purple_transparent_3, R.color.purple_transparent_2, R.color.purple_transparent_1),
            "red" to arrayOf(R.color.red_transparent_3, R.color.red_transparent_2, R.color.red_transparent_1),
            "white" to arrayOf(R.color.white_transparent_3, R.color.white_transparent_2, R.color.white_transparent_1),
            "yellow" to arrayOf(R.color.yellow_transparent_3, R.color.yellow_transparent_2, R.color.yellow_transparent_1),
        )
        var arrayId = colorMap[pokemonColor.toLowerCase()] ?: colorMap["black"] as Array<Int>
        return arrayId.map { ContextCompat.getColor(context,it) }.toIntArray()
    }

    fun getColorTheme(pokemonColor: String, context: Context): Int {
        val colorId = when(pokemonColor) {
            "blue" -> R.color.blue_theme
            "green" -> R.color.green_theme
            "pink" -> R.color.pink_theme
            "purple" -> R.color.purple_theme
            "red" -> R.color.red_theme
            "white" -> R.color.white_theme
            "yellow" -> R.color.yellow_theme
            else -> R.color.black_theme
        }
        return ContextCompat.getColor(context,colorId)
    }

}