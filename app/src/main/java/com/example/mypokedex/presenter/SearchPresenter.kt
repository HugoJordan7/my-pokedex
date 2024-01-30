package com.example.mypokedex.presenter

import android.view.View
import com.example.mypokedex.contract.SearchContract
import com.example.mypokedex.data.SearchPokemonRemoteDataSource
import com.example.mypokedex.model.Pokemon
import com.example.mypokedex.view.PokemonItem
import com.example.mypokedex.view.SearchFragment

class SearchPresenter(
    override var view: SearchFragment,
    override var dataSource: SearchPokemonRemoteDataSource = SearchPokemonRemoteDataSource()
) : SearchContract.Presenter {

    override fun onStart(view: View) {
        this.view.bindAllViews(view)
    }

    override fun findAllPokemonByName(query: String) {
        view.hideRecyclerView()
        view.showProgressBar()
        dataSource.findAllPokemonByName(query,this)
    }

    override fun onSuccess(response: List<Pokemon>) {
        if (response.isEmpty()){
            onFailure("No pokemon found!")
        } else {
            val list = response.map { PokemonItem(it, view.context()) }
            view.showSearchPokemon(list)
        }
    }

    override fun onFailure(message: String) {
        view.showFailure(message)
    }

    override fun onComplete() {
        view.hideProgressBar()
        view.showRecyclerView()
    }

}