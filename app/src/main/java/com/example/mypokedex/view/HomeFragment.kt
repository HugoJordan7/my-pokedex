package com.example.mypokedex.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mypokedex.R
import com.example.mypokedex.contract.HomeContract
import com.example.mypokedex.presenter.HomePresenter
import com.example.mypokedex.util.HomeScroll
import com.xwray.groupie.GroupieAdapter

class HomeFragment : Fragment(), HomeContract.View {

    override lateinit var presenter: HomeContract.Presenter

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private var adapter = GroupieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = HomePresenter(this)
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
        recyclerView.adapter = adapter
        presenter.findAllPokemon(1, HomeScroll.RANGE)
        recyclerView.addOnScrollListener(HomeScroll.getScroll(presenter))
    }

    override fun bindAllViews(view: View) {
        recyclerView = view.findViewById(R.id.rv_home_pokemon)
        progressBar = view.findViewById(R.id.home_progress)
    }

    override fun showPokemon(pokemonList: List<PokemonItem>) {
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

    override fun showFailure(message: String) {
        Toast.makeText(context(), message, Toast.LENGTH_SHORT).show()
    }
}