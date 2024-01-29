package com.example.mypokedex.presenter

import android.view.View
import com.example.mypokedex.contract.SearchContract
import com.example.mypokedex.data.SearchPokemonRemoteDataSource
import com.example.mypokedex.model.Pokemon
import com.example.mypokedex.view.PokemonItem
import com.example.mypokedex.view.SearchFragment

class SearchPresenter(
    override var view: SearchFragment,
    override var dataSource: SearchPokemonRemoteDataSource
) : SearchContract.Presenter {

    override fun onStart(view: View) {
        this.view.bindAllViews(view)
        this.view.showProgressBar()
    }

    override fun findAllSearchPokemon() {
        TODO("Not yet implemented")
    }

    override fun onSuccess(response: List<Pokemon>) {
        view.showSearchPokemon(response.map { PokemonItem(it,view.context()) })
    }

    override fun onFailure(message: String) {
        view.showFailure(message)
    }

    override fun onComplete() {
        view.hideProgressBar()
    }

}