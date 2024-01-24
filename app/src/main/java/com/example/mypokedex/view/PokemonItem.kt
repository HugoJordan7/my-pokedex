package com.example.mypokedex.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.mypokedex.R
import com.example.mypokedex.model.Pokemon
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item


class PokemonItem(var pokemon: Pokemon): Item<PokemonItem.PokemonViewHolder>() {
    inner class PokemonViewHolder(itemView: View): GroupieViewHolder(itemView)
    override fun createViewHolder(itemView: View) = PokemonViewHolder(itemView)
    override fun getLayout() = R.layout.pokemon_item
    override fun bind(viewHolder: PokemonViewHolder, position: Int) {
        var pokemonIcon = viewHolder.itemView.findViewById<ImageView>(R.id.pokemon_icon)
        Picasso.get().load(pokemon.iconUrl).into(pokemonIcon)
        viewHolder.itemView.findViewById<TextView>(R.id.pokemon_name).text = pokemon.name
        viewHolder.itemView.findViewById<TextView>(R.id.pokemon_id).text = viewHolder.itemView.context.getString(R.string.pokemon_id,pokemon.id)
        var primaryType = viewHolder.itemView.findViewById<ImageView>(R.id.primary_type)
        var secondType = viewHolder.itemView.findViewById<ImageView>(R.id.second_type)
        setPrimaryAndSecondPokemonType(pokemon,primaryType,secondType)
    }

    private fun setPrimaryAndSecondPokemonType(pokemon: Pokemon, primaryType: ImageView, secondType: ImageView){
        when(pokemon.primaryType.toLowerCase()){
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
        if(pokemon.secondType == null){

        }else{
            when(pokemon.secondType?.toLowerCase()){
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
}