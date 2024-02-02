package com.example.mypokedex.presenter

import android.util.Log
import android.view.View
import com.example.mypokedex.contract.DetailsContract
import com.example.mypokedex.data.DetailsRemoteDataSource
import com.example.mypokedex.model.Pokemon
import com.example.mypokedex.model.Type
import com.example.mypokedex.view.DetailsFragment

class DetailsPresenter(override var view: DetailsFragment) : DetailsContract.Presenter {

    override var dataSource = DetailsRemoteDataSource(this)

    override fun onStart(view: View) {
        this.view.apply {
            findAllViews(view)
            showProgressBar()
            bindHeader()
            bindAbout()
            bindDescription()
            bindTypes()
            bindStats()
        }
    }

    override fun findWeaknesses(pokemon: Pokemon) {
        val primaryType = pokemon.types[0].name.name
        var secondType: String? = null
        if(pokemon.types.size == 1){
            dataSource.findWeaknesses(primaryType)
        } else{
            secondType = pokemon.types[1].name.name
            dataSource.findWeaknesses(primaryType,secondType)
        }
    }

    override fun onSuccessWeaknesses(primary: Type) {
        var response = primary?.relations?.doubleDamageFrom?.map { it?.name } ?: emptyList()
        view.bindWeaknesses(response)
    }

    override fun onSuccessWeaknesses(primary: Type, second: Type) {

    }

    override fun onFailure(message: String) {
        view.showFailure(message)
    }

    override fun onComplete() {
        view.hideProgressBar()
    }

}