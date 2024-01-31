package com.example.mypokedex.presenter

import android.view.View
import com.example.mypokedex.contract.DetailsContract
import com.example.mypokedex.data.DetailsRemoteDataSource
import com.example.mypokedex.view.DetailsFragment

class DetailsPresenter(
    override var view: DetailsFragment,
    override var dataSource: DetailsRemoteDataSource = DetailsRemoteDataSource()
) : DetailsContract.Presenter {

    override fun onStart(view: View) {
        this.view.findAllViews(view)
        this.view.showProgressBar()
    }

    override fun findPokemonDetails() {

    }

    override fun onSuccess() {
        view.showPokemonDetails()
    }

    override fun onFailure(message: String) {
        view.showFailure(message)
    }

    override fun onComplete() {
        view.hideProgressBar()
    }

}