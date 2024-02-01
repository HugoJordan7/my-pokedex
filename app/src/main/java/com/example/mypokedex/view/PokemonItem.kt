package com.example.mypokedex.view

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import com.example.mypokedex.R
import com.example.mypokedex.model.Pokemon
import com.example.mypokedex.util.ProjectResources
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class PokemonItem(
    var pokemon: Pokemon,
    private var homeFragment: HomeFragment? = null,
    private var searchFragment: SearchFragment? = null
) : Item<PokemonItem.PokemonViewHolder>() {

    constructor(pokemon: Pokemon, view: HomeFragment): this(pokemon,view,null)
    constructor(pokemon: Pokemon, view: SearchFragment): this(pokemon,null,view)

    inner class PokemonViewHolder(itemView: View) : GroupieViewHolder(itemView)

    override fun createViewHolder(itemView: View) = PokemonViewHolder(itemView)

    override fun getLayout() = R.layout.pokemon_item

    override fun bind(viewHolder: PokemonViewHolder, position: Int) {
        val context = if(homeFragment != null) homeFragment!!.context() else searchFragment!!.context()
        pokemon.iconUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/${pokemon.form.id}.png"
        val pokemonIcon = viewHolder.itemView.findViewById<ImageView>(R.id.pokemon_icon)
        Picasso.get().load(pokemon.iconUrl).into(pokemonIcon)
        viewHolder.itemView.findViewById<TextView>(R.id.pokemon_name).text = pokemon.form.name.capitalize()
        viewHolder.itemView.findViewById<TextView>(R.id.pokemon_id).text = context.getString(R.string.pokemon_id, pokemon.form.id)
        val primaryType = viewHolder.itemView.findViewById<ImageView>(R.id.primary_type)
        val secondType = viewHolder.itemView.findViewById<ImageView>(R.id.second_type)

        val typeNameList = pokemon.form.listTypes
        val primaryImage = ProjectResources.getImageByPokemonType(typeNameList[0].name.name)
        primaryType.setImageResource(primaryImage)
        if (typeNameList.size == 2) {
            secondType.visibility = View.VISIBLE
            val secondImage = ProjectResources.getImageByPokemonType(typeNameList[1].name.name)
            secondType.setImageResource(secondImage)
        } else {
            secondType.visibility = View.GONE
        }

        val gradient = GradientDrawable(
            GradientDrawable.Orientation.LEFT_RIGHT,
            ProjectResources.getIntArrayColors(pokemon.specie.color.name, context)
        )
        gradient.cornerRadius = 90f
        viewHolder.itemView.findViewById<ConstraintLayout>(R.id.pokemon_item).background = gradient

        viewHolder.itemView.setOnClickListener {
            var bundle = Bundle()
            bundle.putSerializable(DetailsFragment.POKEMON_KEY,pokemon)
            if (homeFragment != null){
                homeFragment!!.findNavController().navigate(R.id.action_home_fragment_to_details_fragment,bundle)
            } else{
                searchFragment!!.findNavController().navigate(R.id.action_search_fragment_to_details_fragment,bundle)
            }
        }

    }
}