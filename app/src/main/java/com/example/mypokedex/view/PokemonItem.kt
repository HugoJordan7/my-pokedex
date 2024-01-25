package com.example.mypokedex.view

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.alpha
import androidx.core.graphics.toColor
import com.example.mypokedex.R
import com.example.mypokedex.contract.HomeContract
import com.example.mypokedex.model.Pokemon
import com.example.mypokedex.util.ProjectResources
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item


class PokemonItem(
    var pokemon: Pokemon,
    var context: Context
): Item<PokemonItem.PokemonViewHolder>() {

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
        ProjectResources.setPrimaryAndSecondPokemonType(pokemon,primaryType,secondType)

        var gradient = GradientDrawable(
            GradientDrawable.Orientation.RIGHT_LEFT,
            ProjectResources.getIntArrayColors(pokemon.color,context)
        )
        gradient.cornerRadius = 90f
        viewHolder.itemView.findViewById<ConstraintLayout>(R.id.pokemon_item).background = gradient

    }
}