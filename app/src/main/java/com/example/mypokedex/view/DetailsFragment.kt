package com.example.mypokedex.view

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mypokedex.R
import com.example.mypokedex.contract.DetailsContract
import com.example.mypokedex.model.EvolutionItem
import com.example.mypokedex.model.Pokemon
import com.example.mypokedex.presenter.DetailsPresenter
import com.example.mypokedex.util.ProjectResources
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupieAdapter

class DetailsFragment : Fragment(), DetailsContract.View {

    override lateinit var presenter: DetailsPresenter

    companion object {
        const val POKEMON_KEY = "pokemon"
    }

    private lateinit var pokemon: Pokemon
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
    private lateinit var rvStatus: RecyclerView
    private lateinit var rvWeaknesses: RecyclerView
    private lateinit var rvEvolutions: RecyclerView
    private lateinit var layoutEvolutions: LinearLayout
    private lateinit var layoutRvEvo: LinearLayout
    private lateinit var arrayColors: IntArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
        presenter = DetailsPresenter(this)
        pokemon = arguments?.getSerializable(POKEMON_KEY) as Pokemon
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onStart(view)
        presenter.findWeaknesses(pokemon)
        presenter.findEvolutions(pokemon)
    }

    fun bindHeader() {
        val pokeIconUrl = ProjectResources.getPokeImgUrlById(pokemon.id)
        Picasso.get().load(pokeIconUrl).into(pokemonIcon)
        val colorName: String = pokemon.specie?.color?.name ?: "black"
        arrayColors = ProjectResources.getIntArrayColors(colorName, context())
        val gradient = ContextCompat.getDrawable(
            context(),
            R.drawable.details_header_shape
        ) as GradientDrawable
        gradient.colors = arrayColors
        layoutHeader.background = gradient
    }

    fun bindAbout() {
        pokemonName.text = getString(R.string.about_name, pokemon.name.capitalize())
        pokemonId.text = getString(R.string.about_id, pokemon.id)
        pokemonHeight.text = getString(R.string.about_height, (pokemon.height / 10.0))
        pokemonWeight.text = getString(R.string.about_weight, (pokemon.weight / 10))
        pokemonHabitat.text = getString(
            R.string.about_habitat,
            (pokemon.specie?.habitat?.name?.capitalize() ?: getString(R.string.unknown))
        )
    }

    fun bindTypes() {
        val type1 = ProjectResources.getImageByPokemonType(pokemon.types[0].name.name)
        primaryType.setImageResource(type1)
        if (pokemon.types.size > 1) {
            val type2 = ProjectResources.getImageByPokemonType(pokemon.types[1].name.name)
            secondType.setImageResource(type2)
        }
    }

    fun bindDescription() { // 6 - Ruby/English and 42 - OmegaRuby/Spanish
        pokemonDescription.text =
            pokemon.specie?.descriptionList?.get(42)?.text ?: getString(R.string.unknown)
    }

    fun bindStats() {
        val adapterStatus = GroupieAdapter()
        adapterStatus.addAll(StatusItem.getStatusItemList(context(), pokemon))
        adapterStatus.notifyDataSetChanged()
        rvStatus.layoutManager = GridLayoutManager(context(), 2)
        rvStatus.adapter = adapterStatus
    }

    fun bindWeaknesses(weakTypesList: List<String>){
        rvWeaknesses.layoutManager = GridLayoutManager(context(),2)
        val weakAdapter = GroupieAdapter()
        rvWeaknesses.adapter = weakAdapter
        weakAdapter.addAll(TypeItem.getTypeItemListByNames(weakTypesList))
        weakAdapter.notifyDataSetChanged()
    }

    fun bindEvolutions(list: List<EvolutionItem>, columns: Int){
        if(columns > 1) layoutEvolutions.visibility = View.VISIBLE
        var gradient = ContextCompat.getDrawable(context(),R.drawable.evolutions_shape) as GradientDrawable
        val color = ProjectResources.getEvolutionTheme(pokemon.specie?.color?.name ?: "black",context())
        gradient.setColor(color)
        layoutRvEvo.background = gradient
        rvEvolutions.layoutManager = GridLayoutManager(context(),columns)
        rvEvolutions.adapter = EvolutionAdapter(list,context())
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun showFailure(message: String) {
        Toast.makeText(context(), message, Toast.LENGTH_SHORT).show()
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
        rvStatus = view.findViewById(R.id.rv_status)
        rvWeaknesses = view.findViewById(R.id.rv_weaknesses)
        rvEvolutions = view.findViewById(R.id.rv_evolutions)
        layoutEvolutions = view.findViewById(R.id.details_evolutions)
        layoutRvEvo = view.findViewById(R.id.rv_evolutions_layout)
    }

}