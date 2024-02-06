package com.example.mypokedex.contract

import android.content.Context
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import com.example.mypokedex.data.UnknownRemoteDataSource
import com.example.mypokedex.model.Pokemon
import com.example.mypokedex.model.PokemonNamesList
import com.example.mypokedex.presenter.UnknownPresenter
import com.example.mypokedex.view.PokemonItem
import com.example.mypokedex.view.UnknownFragment

interface UnknownContract {

    interface View{
        var presenter: UnknownPresenter
        fun findAllElements(view: android.view.View)
        fun showPokemon(pokemon: Pokemon)
        fun context(): Context
        fun showProgressBar()
        fun hideProgressBar()
        fun showFailure(message: String)
        fun showResult(message: String)
        fun setAutoCompleteAdapter(results: List<String>)
    }

    interface Presenter{
        var view: UnknownFragment
        var dataSource: UnknownRemoteDataSource
        fun onStart(view: android.view.View)
        fun findRandomPokemon()
        fun onSuccess(pokemon: Pokemon)
        fun onFailure(message: String)
        fun onComplete()
        fun checkResult(autoComplete: AutoCompleteTextView, button: Button, pokemon: Pokemon)
        fun getPokemonNamesList()
        fun onSuccessPokeList(pokemonNamesList: PokemonNamesList)
    }
}