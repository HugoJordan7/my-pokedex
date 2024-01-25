package com.example.mypokedex.contract

import android.content.Context
import android.view.View
import com.example.mypokedex.data.PokemonRemoteDataSource
import com.example.mypokedex.model.Pokemon
import com.example.mypokedex.view.PokemonItem

interface HomeContract {

    interface View{
        var presenter: Presenter
        fun bindAllViews(view: android.view.View)
        fun showPokemon(listPokemon: List<PokemonItem>)
        fun context(): Context
        fun showProgressBar()
        fun hideProgressBar()
        fun showFailure(message: String)
    }

    interface Presenter{
        var view: View
        var dataSource: PokemonRemoteDataSource
        fun onStart(view: android.view.View)
        fun findAllPokemon()
        fun onSuccess(response: List<Pokemon>)
        fun onFailure(message: String)
        fun onComplete()
    }

}