package com.example.mypokedex.view

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.mypokedex.R
import com.example.mypokedex.contract.DetailsContract
import com.example.mypokedex.model.Pokemon
import com.example.mypokedex.presenter.DetailsPresenter
import com.example.mypokedex.util.ProjectResources
import com.squareup.picasso.Picasso

class DetailsFragment: Fragment(), DetailsContract.View {

    override lateinit var presenter: DetailsPresenter

    companion object{ const val POKEMON_KEY = "pokemon" }

    private lateinit var progressBar: ProgressBar
    private lateinit var layoutHeader: LinearLayout
    private lateinit var pokemonIcon: ImageView
    private lateinit var pokemonName: TextView
    private lateinit var pokemonId: TextView
    private lateinit var pokemonHeight: TextView
    private lateinit var pokemonWeight: TextView
    private lateinit var pokemonHabitat: TextView
    private lateinit var primaryType: ImageView
    private lateinit var secondType: ImageView
    private lateinit var pokemonDescription: TextView
    private lateinit var hpStatus: TextView
    private lateinit var attackStatus: TextView
    private lateinit var defenseStatus: TextView
    private lateinit var specialAttackStatus: TextView
    private lateinit var specialDefenseStatus: TextView
    private lateinit var speedStatus: TextView
    private lateinit var rvWeaknesses: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
        presenter = DetailsPresenter(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_details,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pokemon = (arguments?.getSerializable(POKEMON_KEY)
            ?: throw Exception("Error search pokemon details")) as Pokemon
        presenter.onStart(view)

        Picasso.get().load(pokemon.iconUrl).into(pokemonIcon)
        pokemonName.text = getString(R.string.about_name, pokemon.name.capitalize())
        pokemonId.text = getString(R.string.about_id, pokemon.id)

        val type1 = ProjectResources.getImageByPokemonType(pokemon.types[0].name.name)
        primaryType.setImageResource(type1)
        if (pokemon.types.size > 1) {
            val type2 = ProjectResources.getImageByPokemonType(pokemon.types[1].name.name)
            secondType.setImageResource(type2)
        }
        val arrayColors = pokemon.specie?.color?.let { ProjectResources.getIntArrayColors(it.name, context()) }
        val gradient = ContextCompat.getDrawable(context(),R.drawable.details_header_shape) as GradientDrawable
        gradient.colors = arrayColors
        layoutHeader.background = gradient
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun showPokemonDetails() {

    }

    override fun showFailure(message: String) {
        Toast.makeText(context(),message,Toast.LENGTH_SHORT).show()
    }

    override fun context() = requireContext()

    override fun findAllViews(view: View) {
        progressBar = view.findViewById(R.id.progress_details)
        layoutHeader = view.findViewById(R.id.details_header)
        pokemonIcon = view.findViewById(R.id.details_poke_icon)
        pokemonName = view.findViewById(R.id.about_name)
        pokemonId = view.findViewById(R.id.about_id)
        pokemonHeight = view.findViewById(R.id.about_height)
        pokemonWeight = view.findViewById(R.id.about_weight)
        pokemonHabitat = view.findViewById(R.id.about_habitat)
        primaryType = view.findViewById(R.id.details_primary_type)
        secondType = view.findViewById(R.id.details_second_type)
        pokemonDescription = view.findViewById(R.id.details_description_text)
        hpStatus = view.findViewById(R.id.hp_status)
        attackStatus = view.findViewById(R.id.attack_status)
        defenseStatus = view.findViewById(R.id.defense_status)
        specialAttackStatus = view.findViewById(R.id.special_attack_status)
        specialDefenseStatus = view.findViewById(R.id.special_defense_status)
        speedStatus = view.findViewById(R.id.speed_status)
        rvWeaknesses = view.findViewById(R.id.rv_weaknesses)
    }

}