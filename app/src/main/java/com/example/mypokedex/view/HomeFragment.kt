package com.example.mypokedex.view

import android.graphics.Color
import android.os.Bundle
import android.text.InputFilter
import android.view.*
import android.widget.*
import androidx.appcompat.widget.ButtonBarLayout
import androidx.appcompat.widget.SearchView
import androidx.core.view.marginBottom
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mypokedex.R
import com.example.mypokedex.contract.HomeContract
import com.example.mypokedex.presenter.HomePresenter
import com.example.mypokedex.util.HomeAdapter
import com.example.mypokedex.util.HomeScroll
import com.xwray.groupie.GroupieAdapter

class HomeFragment : Fragment(), HomeContract.View {

    override lateinit var presenter: HomeContract.Presenter

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    private lateinit var searchBar: View
    private lateinit var searchEditText: EditText
    private lateinit var searchCloseButton: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = HomePresenter(this)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onStart(view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = HomeAdapter.adapter
        if (HomeAdapter.adapter.itemCount == 0) {
            presenter.findAllPokemon(1, HomeScroll.RANGE)
        }
        recyclerView.addOnScrollListener(HomeScroll.getScroll(presenter))
    }

    override fun bindAllViews(view: View) {
        recyclerView = view.findViewById(R.id.rv_home_pokemon)
        progressBar = view.findViewById(R.id.home_progress)
    }

    override fun showPokemon(pokemonList: List<PokemonItem>) {
        HomeAdapter.adapter.addAll(pokemonList)
        HomeAdapter.adapter.notifyDataSetChanged()
    }

    override fun context() = requireContext()

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun showFailure(message: String) {
        Toast.makeText(context(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        val searchItem = menu.findItem(R.id.item_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(context(), "search pokemon", Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Toast.makeText(context(), "zzzzzz", Toast.LENGTH_SHORT).show()
                return true
            }
        })
        customizingActionBar(searchView)
    }

    private fun customizingActionBar(searchView: SearchView) {
        bindAllSearchViewElements(searchView)
        searchEditText.apply {
            setHint(R.string.search)
            textAlignment = View.TEXT_ALIGNMENT_CENTER
            setSingleLine()
            filters = arrayOf<InputFilter>(InputFilter.LengthFilter(15))
        }
        searchBar.setBackgroundResource(R.drawable.search_view_shape)
        searchBar.layoutParams = (searchBar.layoutParams as ViewGroup.MarginLayoutParams).apply {
            topMargin = 15
            bottomMargin = 15
            rightMargin = 40
        }
    }

    private fun bindAllSearchViewElements(searchView: SearchView){
        searchEditText = searchView.findViewById(androidx.appcompat.R.id.search_src_text)
        searchBar = searchView.findViewById(androidx.appcompat.R.id.search_bar)
        searchCloseButton = searchView.findViewById(androidx.appcompat.R.id.search_close_btn)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

}