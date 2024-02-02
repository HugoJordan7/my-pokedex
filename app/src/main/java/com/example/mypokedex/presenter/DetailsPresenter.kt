package com.example.mypokedex.presenter

import android.view.View
import com.example.mypokedex.contract.DetailsContract
import com.example.mypokedex.view.DetailsFragment

class DetailsPresenter(override var view: DetailsFragment) : DetailsContract.Presenter {

    override fun onStart(view: View) {
        this.view.apply {
            findAllViews(view)

            showProgressBar()

            bindHeader()
            bindAbout()
            bindDescription()
            bindTypes()
            bindStats()
            bindWeaknesses()

            hideProgressBar()
        }
    }

    override fun findPokemonDetails() {

    }

    override fun onSuccess() {

    }

    override fun onFailure(message: String) {
        view.showFailure(message)
    }

    override fun onComplete() {
        view.hideProgressBar()
    }

}