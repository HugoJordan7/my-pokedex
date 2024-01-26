package com.example.mypokedex.presenter

import android.view.View
import com.example.mypokedex.contract.HomeContract
import com.example.mypokedex.data.PokemonRemoteDataSource
import com.example.mypokedex.model.Pokemon
import com.example.mypokedex.view.PokemonItem

class HomePresenter(
    override var view: HomeContract.View,
    override var dataSource: PokemonRemoteDataSource = PokemonRemoteDataSource()
) : HomeContract.Presenter {

    override fun onStart(view: View) {
        this.view.bindAllViews(view)
        this.view.showProgressBar()
    }

    override fun findAllPokemon(firstId: Int, lastId: Int) {
        dataSource.findAllPokemon(this,firstId,lastId)
    }

    override fun onSuccess(response: List<Pokemon>) {
        var list = response.map { PokemonItem(it,view.context()) }
        view.showPokemon(list)
    }

    override fun onFailure(message: String) {
        view.showFailure(message)
    }

    override fun onComplete() {
        view.hideProgressBar()
    }

}