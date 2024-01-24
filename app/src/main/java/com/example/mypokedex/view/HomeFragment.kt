package com.example.mypokedex.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mypokedex.R
import com.example.mypokedex.contract.HomeContract
import com.example.mypokedex.model.Pokemon
import com.example.mypokedex.presenter.HomePresenter
import com.xwray.groupie.GroupieAdapter

class HomeFragment() : Fragment(), HomeContract.View {

    override lateinit var presenter: HomeContract.Presenter

    private lateinit var recyclerView: RecyclerView
    private var adapter = GroupieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = HomePresenter(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onStart(view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        presenter.findAllPokemon()
    }

    override fun bindAllViews(view: View) {
        recyclerView = view.findViewById(R.id.rv_home_pokemon)
    }

    override fun showPokemon(listPokemon: List<PokemonItem>) {
        adapter.addAll(listPokemon)
        adapter.notifyDataSetChanged()
    }

}