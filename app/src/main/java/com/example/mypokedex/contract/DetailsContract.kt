package com.example.mypokedex.contract

import android.content.Context
import com.example.mypokedex.data.DetailsRemoteDataSource
import com.example.mypokedex.model.Pokemon
import com.example.mypokedex.model.Type
import com.example.mypokedex.presenter.DetailsPresenter
import com.example.mypokedex.view.DetailsFragment

interface DetailsContract {

    interface View {
        var presenter: DetailsPresenter
        fun findAllViews(view: android.view.View)
        fun showProgressBar()
        fun hideProgressBar()
        fun showFailure(message: String)
        fun context(): Context
    }

    interface Presenter {
        var view: DetailsFragment
        var dataSource: DetailsRemoteDataSource
        fun onStart(view: android.view.View)
        fun findWeaknesses(pokemon: Pokemon)
        fun onSuccessWeaknesses(primary: Type)
        fun onSuccessWeaknesses(primary: Type, second: Type)
        fun onComplete()
        fun onFailure(message: String)
    }
}