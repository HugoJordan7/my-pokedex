package com.example.mypokedex.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.mypokedex.R
import com.example.mypokedex.contract.UnknownContract
import com.example.mypokedex.model.Pokemon
import com.example.mypokedex.presenter.UnknownPresenter
import com.example.mypokedex.util.ProjectResources
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UnknownFragment : Fragment(), UnknownContract.View {

    override lateinit var presenter: UnknownPresenter

    private lateinit var pokemon: Pokemon
    private lateinit var progressBar: ProgressBar
    private lateinit var imageView: ImageView
    private lateinit var title: TextView
    private lateinit var autoComplete: AutoCompleteTextView
    private lateinit var buttonSend: Button
    private var pokeNamesList: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        presenter = UnknownPresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_unknown_pokemon, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onStart(view)
        presenter.findRandomPokemon()
        presenter.getPokemonNamesList()
        buttonSend.setOnClickListener {
            presenter.checkResult(autoComplete,buttonSend,pokemon)
        }
        autoComplete.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val userInput = s.toString().toLowerCase()
                val filteredSuggestions = pokeNamesList.filter { it.toLowerCase().startsWith(userInput) }

                val adapter = ArrayAdapter(context(), android.R.layout.simple_dropdown_item_1line, filteredSuggestions)
                autoComplete.setAdapter(adapter)
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    override fun showPokemon(pokemon: Pokemon) {
        this.pokemon = pokemon
        lifecycleScope.launch(Dispatchers.IO) {
            val bitmap = Picasso.get().load(ProjectResources.getPokeImgUrlById(pokemon.id)).get()
            withContext(Dispatchers.Main) {
                presenter.setSilhouette(imageView, bitmap)
            }
        }
    }

    override fun showResult(message: String) {
        Toast.makeText(context(),message,Toast.LENGTH_SHORT).show()
        title.text = pokemon.name
        val pokeImage = ProjectResources.getPokeImgUrlById(pokemon.id)
        Picasso.get().load(pokeImage).into(imageView)
    }

    override fun setAutoCompleteAdapter(results: List<String>) {
        pokeNamesList.addAll(results)
        val adapter = ArrayAdapter(
            context(),
            android.R.layout.simple_dropdown_item_1line,
            results
        )
        autoComplete.setAdapter(adapter)
    }

    override fun context() = requireContext()

    override fun showProgressBar(){
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar(){
        progressBar.visibility = View.GONE
    }

    override fun showFailure(message: String) {
        Toast.makeText(context(),message,Toast.LENGTH_SHORT).show()
    }

    override fun findAllElements(view: View){
        progressBar = view.findViewById(R.id.unknown_progress)
        imageView = view.findViewById(R.id.unknown_image)
        title = view.findViewById(R.id.unknown_poke_title)
        buttonSend = view.findViewById(R.id.button_send_unknown)
        autoComplete = view.findViewById(R.id.auto_complete_text_unknown)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_unknown,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_unknown && !progressBar.isVisible){
            findNavController().popBackStack()
            findNavController().navigate(R.id.unknown_pokemon_fragment)
        }
        return super.onOptionsItemSelected(item)
    }

}