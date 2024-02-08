package com.example.mypokedex.view

import android.graphics.Color
import android.os.Bundle
import android.text.InputFilter
import android.view.*
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mypokedex.R
import com.example.mypokedex.contract.SearchContract
import com.example.mypokedex.presenter.SearchPresenter
import com.xwray.groupie.GroupieAdapter
import okhttp3.internal.notifyAll

class SearchFragment : Fragment(), SearchContract.View {

    override lateinit var presenter: SearchPresenter

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private var adapter = GroupieAdapter()

    private lateinit var searchBar: View
    private lateinit var searchEditText: EditText
    private lateinit var searchCloseButton: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        presenter = SearchPresenter(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onStart(view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

    override fun bindAllViews(view: View) {
        recyclerView = view.findViewById(R.id.rv_search_pokemon)
        progressBar = view.findViewById(R.id.search_progress)
    }

    override fun showSearchPokemon(pokemonList: List<PokemonItem>) {
        adapter.addAll(pokemonList)
        adapter.notifyDataSetChanged()
    }

    override fun context() = requireContext()

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun showRecyclerView() {
        recyclerView.visibility = View.VISIBLE
    }

    override fun hideRecyclerView() {
        recyclerView.visibility = View.GONE
    }

    override fun showFailure(message: String) {
        Toast.makeText(context(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        val searchItem = menu.findItem(R.id.item_search)
        val searchView = searchItem.actionView as SearchView
        searchItem.expandActionView()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query == null) {
                    Toast.makeText(requireContext(), "The query is empty!", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    adapter.clear()
                    presenter.findAllPokemonByName(query)
                }
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return true
            }
        })
        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                //Actions when searchItem is open
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                //Actions when searchItem is close
                findNavController().popBackStack()
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
            filters = arrayOf<InputFilter>(InputFilter.LengthFilter(15))
        }
        searchBar.setBackgroundResource(R.drawable.search_view_shape)
        searchBar.layoutParams = (searchBar.layoutParams as ViewGroup.MarginLayoutParams).apply {
            topMargin = 15
            bottomMargin = 15
            rightMargin = 40
        }
    }

    private fun bindAllSearchViewElements(searchView: SearchView) {
        searchEditText = searchView.findViewById(androidx.appcompat.R.id.search_src_text)
        searchBar = searchView.findViewById(androidx.appcompat.R.id.search_bar)
        searchCloseButton = searchView.findViewById(androidx.appcompat.R.id.search_close_btn)
    }


}