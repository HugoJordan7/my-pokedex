package com.example.mypokedex.contract

import android.content.Context
import com.example.mypokedex.data.PokemonRemoteDataSource
import com.example.mypokedex.data.SearchPokemonRemoteDataSource
import com.example.mypokedex.model.Pokemon
import com.example.mypokedex.presenter.HomePresenter
import com.example.mypokedex.presenter.SearchPresenter
import com.example.mypokedex.view.HomeFragment
import com.example.mypokedex.view.PokemonItem
import com.example.mypokedex.view.SearchFragment

interface SearchContract {

    interface View{
        var presenter: SearchPresenter
        fun bindAllViews(view: android.view.View)
        fun showSearchPokemon(pokemonList: List<PokemonItem>)
        fun context(): Context
        fun showProgressBar()
        fun hideProgressBar()
        fun showFailure(message: String)
    }

    interface Presenter{
        var view: SearchFragment
        var dataSource: SearchPokemonRemoteDataSource
        fun onStart(view: android.view.View)
        fun findAllSearchPokemon()
        fun onSuccess(response: List<Pokemon>)
        fun onFailure(message: String)
        fun onComplete()
    }

}