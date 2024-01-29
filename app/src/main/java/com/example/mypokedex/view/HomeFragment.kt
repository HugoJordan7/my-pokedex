package com.example.mypokedex.view

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mypokedex.R
import com.example.mypokedex.contract.HomeContract
import com.example.mypokedex.presenter.HomePresenter
import com.example.mypokedex.util.HomeAdapter
import com.example.mypokedex.util.HomeScroll

class HomeFragment : Fragment(), HomeContract.View {

    override lateinit var presenter: HomePresenter

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

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
        inflater.inflate(R.menu.menu_home,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.search_home -> {
                findNavController().navigate(R.id.action_home_fragment_to_search_fragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}