package com.example.mypokedex.presenter

import android.view.View
import com.example.mypokedex.contract.HomeContract
import com.example.mypokedex.data.PokemonRemoteDataSource
import com.example.mypokedex.model.*
import com.example.mypokedex.util.HomeScroll
import com.example.mypokedex.view.HomeFragment
import com.example.mypokedex.view.PokemonItem


class HomePresenter(
    override var view: HomeFragment,
    override var dataSource: PokemonRemoteDataSource = PokemonRemoteDataSource()
) : HomeContract.Presenter {

    override fun onStart(view: View) {
        this.view.bindAllViews(view)
    }

    override fun findAllPokemon(firstId: Int, lastId: Int) {
        view.showProgressBar()
        dataSource.findAllPokemon(this, firstId, lastId)
    }

    override fun loadMorePokemon(currentId: Int) {
        var lastId = currentId + HomeScroll.RANGE-1
        findAllPokemon(currentId, lastId)
    }

    override fun onSuccess(response: List<Pokemon>) {
        var list = response.map { PokemonItem(it, view.context()) }
        view.showPokemon(list)
    }

    override fun onFailure(message: String) {
        view.showFailure(message)
    }

    override fun onComplete() {
        view.hideProgressBar()
    }

}