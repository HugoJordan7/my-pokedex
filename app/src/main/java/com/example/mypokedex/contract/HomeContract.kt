package com.example.mypokedex.contract

import android.content.Context
import com.example.mypokedex.data.PokemonRemoteDataSource
import com.example.mypokedex.model.Pokemon
import com.example.mypokedex.presenter.HomePresenter
import com.example.mypokedex.view.HomeFragment
import com.example.mypokedex.view.PokemonItem

interface HomeContract {

    interface View{
        var presenter: HomePresenter
        fun bindAllViews(view: android.view.View)
        fun showPokemon(pokemonList: List<PokemonItem>)
        fun showSearchPokemon(pokemonList: List<PokemonItem>)
        fun context(): Context
        fun showProgressBar()
        fun hideProgressBar()
        fun showFailure(message: String)
    }

    interface Presenter{
        var view: HomeFragment
        var dataSource: PokemonRemoteDataSource
        fun onStart(view: android.view.View)
        fun findAllPokemon(firstId: Int, lastId: Int)
        fun loadMorePokemon(currentId: Int)
        fun findAllSearchPokemon()
        fun onSuccess(response: List<Pokemon>)
        fun onFailure(message: String)
        fun onComplete()
    }

}