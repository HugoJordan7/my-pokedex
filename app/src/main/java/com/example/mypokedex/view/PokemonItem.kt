package com.example.mypokedex.view

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.mypokedex.R
import com.example.mypokedex.model.Pokemon
import com.example.mypokedex.util.ProjectResources
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class PokemonItem(
    var pokemon: Pokemon,
    private var context: Context
): Item<PokemonItem.PokemonViewHolder>() {

    inner class PokemonViewHolder(itemView: View): GroupieViewHolder(itemView)

    override fun createViewHolder(itemView: View) = PokemonViewHolder(itemView)

    override fun getLayout() = R.layout.pokemon_item

    override fun bind(viewHolder: PokemonViewHolder, position: Int) {
        pokemon.form.iconUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/${pokemon.form.id}.png"
        val pokemonIcon = viewHolder.itemView.findViewById<ImageView>(R.id.pokemon_icon)
        Picasso.get().load(pokemon.form.iconUrl).into(pokemonIcon)
        viewHolder.itemView.findViewById<TextView>(R.id.pokemon_name).text = pokemon.form.name
        viewHolder.itemView.findViewById<TextView>(R.id.pokemon_id).text = viewHolder.itemView.context.getString(R.string.pokemon_id,pokemon.form.id)
        val primaryType = viewHolder.itemView.findViewById<ImageView>(R.id.primary_type)
        val secondType = viewHolder.itemView.findViewById<ImageView>(R.id.second_type)

        val typeNameList = pokemon.form.listTypes
        if(typeNameList.isNotEmpty()){
            ProjectResources.setImageByPokemonType(typeNameList[0].name.name,primaryType)
            if (typeNameList.size == 2){
                ProjectResources.setImageByPokemonType(typeNameList[1].name.name,secondType)
            }
        }

        val gradient = GradientDrawable(
            GradientDrawable.Orientation.RIGHT_LEFT,
            ProjectResources.getIntArrayColors(pokemon.specie.color.name,context)
        )
        gradient.cornerRadius = 90f
        viewHolder.itemView.findViewById<ConstraintLayout>(R.id.pokemon_item).background = gradient

    }
}