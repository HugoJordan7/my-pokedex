package com.example.mypokedex.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mypokedex.R
import com.example.mypokedex.contract.DetailsContract
import com.example.mypokedex.model.Pokemon
import com.example.mypokedex.presenter.DetailsPresenter

class DetailsFragment: Fragment(), DetailsContract.View {

    override lateinit var presenter: DetailsPresenter

    companion object{ const val POKEMON_KEY = "pokemon" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = DetailsPresenter(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_details,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pokemon = (arguments?.getSerializable(POKEMON_KEY) ?: throw Exception("Error search pokemon details")) as Pokemon
    }

    override fun findAllViews(view: View) {

    }

    override fun showProgressBar() {

    }

    override fun hideProgressBar() {

    }

    override fun showPokemonDetails() {

    }

    override fun showFailure(message: String) {

    }

    override fun context() = requireContext()
}