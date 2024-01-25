package com.example.mypokedex.util

import android.content.Context
import android.graphics.Color
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.example.mypokedex.R
import com.example.mypokedex.model.Pokemon

object ProjectResources {

    fun setPrimaryAndSecondPokemonType(
        pokemon: Pokemon,
        primaryType: ImageView,
        secondType: ImageView
    ) {
        when (pokemon.primaryType.toLowerCase()) {
            "normal" -> primaryType.setImageResource(R.drawable.normal_type)
            "fighting" -> primaryType.setImageResource(R.drawable.fight_type)
            "flying" -> primaryType.setImageResource(R.drawable.flying_type)
            "poison" -> primaryType.setImageResource(R.drawable.poison_type)
            "ground" -> primaryType.setImageResource(R.drawable.ground_type)
            "rock" -> primaryType.setImageResource(R.drawable.rock_type)
            "bug" -> primaryType.setImageResource(R.drawable.bug_type)
            "ghost" -> primaryType.setImageResource(R.drawable.ghost_type)
            "steel" -> primaryType.setImageResource(R.drawable.steel_type)
            "fire" -> primaryType.setImageResource(R.drawable.fire_type)
            "water" -> primaryType.setImageResource(R.drawable.water_type)
            "grass" -> primaryType.setImageResource(R.drawable.grass_type)
            "electric" -> primaryType.setImageResource(R.drawable.electric_type)
            "psychic" -> primaryType.setImageResource(R.drawable.psychic_type)
            "ice" -> primaryType.setImageResource(R.drawable.ice_type)
            "dragon" -> primaryType.setImageResource(R.drawable.dragon_type)
            "dark" -> primaryType.setImageResource(R.drawable.dark_type)
            "fairy" -> primaryType.setImageResource(R.drawable.fairy_type)
        }
        if (pokemon.secondType == null) {

        } else {
            when (pokemon.secondType?.toLowerCase()) {
                "normal" -> secondType.setImageResource(R.drawable.normal_type)
                "fighting" -> secondType.setImageResource(R.drawable.fight_type)
                "flying" -> secondType.setImageResource(R.drawable.flying_type)
                "poison" -> secondType.setImageResource(R.drawable.poison_type)
                "ground" -> secondType.setImageResource(R.drawable.ground_type)
                "rock" -> secondType.setImageResource(R.drawable.rock_type)
                "bug" -> secondType.setImageResource(R.drawable.bug_type)
                "ghost" -> secondType.setImageResource(R.drawable.ghost_type)
                "steel" -> secondType.setImageResource(R.drawable.steel_type)
                "fire" -> secondType.setImageResource(R.drawable.fire_type)
                "water" -> secondType.setImageResource(R.drawable.water_type)
                "grass" -> secondType.setImageResource(R.drawable.grass_type)
                "electric" -> secondType.setImageResource(R.drawable.electric_type)
                "psychic" -> secondType.setImageResource(R.drawable.psychic_type)
                "ice" -> secondType.setImageResource(R.drawable.ice_type)
                "dragon" -> secondType.setImageResource(R.drawable.dragon_type)
                "dark" -> secondType.setImageResource(R.drawable.dark_type)
                "fairy" -> secondType.setImageResource(R.drawable.fairy_type)
            }
        }
    }

    fun getIntArrayColors(pokemonColor: String, context: Context): IntArray {
        var gradientColor1: Int = R.color.red_transparent_1
        var gradientColor2 = R.color.red_transparent_2
        var gradientColor3: Int = R.color.red_transparent_3
        when (pokemonColor.toLowerCase()) {
            "black" -> {
                gradientColor1 = R.color.black_transparent_1
                gradientColor2 = R.color.black_transparent_2
                gradientColor3 = R.color.black_transparent_3
            }
            "blue" -> {
                gradientColor1 = R.color.blue_transparent_1
                gradientColor2 = R.color.blue_transparent_2
                gradientColor3 = R.color.blue_transparent_3
            }
            "green" -> {
                gradientColor1 = R.color.green_transparent_1
                gradientColor2 = R.color.green_transparent_2
                gradientColor3 = R.color.green_transparent_3
            }
            "pink" -> {
                gradientColor1 = R.color.pink_transparent_1
                gradientColor2 = R.color.pink_transparent_2
                gradientColor3 = R.color.pink_transparent_3
            }
            "purple" -> {
                gradientColor1 = R.color.purple_transparent_1
                gradientColor2 = R.color.purple_transparent_2
                gradientColor3 = R.color.purple_transparent_3
            }
            "red" -> {
                gradientColor1 = R.color.red_transparent_1
                gradientColor2 = R.color.red_transparent_2
                gradientColor3 = R.color.red_transparent_3
            }
            "white" -> {
                gradientColor1 = R.color.write_transparent_1
                gradientColor2 = R.color.write_transparent_2
                gradientColor3 = R.color.write_transparent_3
            }
            "yellow" -> {
                gradientColor1 = R.color.yellow_transparent_1
                gradientColor2 = R.color.yellow_transparent_2
                gradientColor3 = R.color.yellow_transparent_3
            }
        }

        return intArrayOf(
            ContextCompat.getColor(context,gradientColor1),
            ContextCompat.getColor(context,gradientColor2),
            ContextCompat.getColor(context,gradientColor3),
        )
    }

}