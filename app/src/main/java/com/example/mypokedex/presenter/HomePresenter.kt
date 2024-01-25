package com.example.mypokedex.presenter

import android.os.Handler
import android.os.Looper
import android.view.View
import com.example.mypokedex.contract.HomeContract
import com.example.mypokedex.data.PokemonRemoteDataSource
import com.example.mypokedex.model.Pokemon
import com.example.mypokedex.util.ProjectResources
import com.example.mypokedex.view.PokemonItem

class HomePresenter(
    override var view: HomeContract.View,
    override var dataSource: PokemonRemoteDataSource = PokemonRemoteDataSource()
) : HomeContract.Presenter {

    override fun onStart(view: View) {
        this.view.bindAllViews(view)
        this.view.showProgressBar()
    }

    override fun findAllPokemon() {
        dataSource.findAllPokemon(this)
    }

    override fun onSuccess(response: List<Pokemon>) {
        var listItem = response.map { PokemonItem(it, view.context()) }
        view.showPokemon(listItem)
        onComplete()
    }

    override fun onFailure(message: String) {
        view.showFailure(message)
        onComplete()
    }

    override fun onComplete() {
        view.hideProgressBar()
    }

}