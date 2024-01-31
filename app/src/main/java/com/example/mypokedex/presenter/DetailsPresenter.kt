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

    }

    override fun findPokemonDetails() {

    }

    override fun onSuccess() {

    }

    override fun onFailure() {

    }

    override fun onComplete() {

    }

}