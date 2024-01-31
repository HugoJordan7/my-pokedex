package com.example.mypokedex.contract

import android.content.Context
import com.example.mypokedex.data.DetailsRemoteDataSource
import com.example.mypokedex.presenter.DetailsPresenter
import com.example.mypokedex.view.DetailsFragment

interface DetailsContract {

    interface View {
        var presenter: DetailsPresenter
        fun findAllViews(view: android.view.View)
        fun showProgressBar()
        fun hideProgressBar()
        fun showPokemonDetails()
        fun showFailure(message: String)
        fun context(): Context
    }

    interface Presenter {
        var view: DetailsFragment
        var dataSource: DetailsRemoteDataSource
        fun onStart(view: android.view.View)
        fun findPokemonDetails()
        fun onSuccess()
        fun onFailure()
        fun onComplete()
    }
}