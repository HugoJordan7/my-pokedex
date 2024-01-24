package com.example.mypokedex.contract

import android.view.View
import com.example.mypokedex.model.Pokemon
import com.example.mypokedex.view.PokemonItem

interface HomeContract {

    interface View{
        var presenter: Presenter
        fun bindAllViews(view: android.view.View)
        fun showPokemon(listPokemon: List<PokemonItem>)
    }

    interface Presenter{
        var view: View
        fun onStart(view: android.view.View)
        fun findAllPokemon()
    }

}