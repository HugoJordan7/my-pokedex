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
            bindEvolutions()
        }
    }

    override fun findWeaknesses(pokemon: Pokemon) {
        val primaryType = pokemon.types[0].name.name.toLowerCase()
        val secondType: String? =  if(pokemon.types.size > 1) pokemon.types[1].name.name.toLowerCase() else null
        dataSource.findWeaknesses(primaryType, secondType)
    }

    override fun onSuccessWeaknesses(primary: Type) {
        var list = primary?.relations?.doubleDamageFrom?.map { it?.name } ?: emptyList()
        val response: MutableList<String> = mutableListOf()
        if(list.isNotEmpty()){
            val noDamageList = primary?.relations?.noDamageFrom?.map { it?.name } ?: emptyList()
            response.addAll(list)
            for (type in noDamageList){
                response.filter { it == type }
            }
        }
        view.bindWeaknesses(response)
    }

    override fun onSuccessWeaknesses(primary: Type, second: Type) {
        val primaryListWeak = primary?.relations?.doubleDamageFrom?.map { it?.name } as MutableList<String>
        val secondListStrong = second?.relations?.halfDamageFrom?.map { it?.name } as MutableList<String>
        primaryListWeak.removeAll(secondListStrong)

        val secondListWeak = second?.relations?.doubleDamageFrom?.map { it?.name } as MutableList<String>
        val primaryListStrong = primary?.relations?.halfDamageFrom?.map { it?.name } as MutableList<String>
        secondListWeak.removeAll(primaryListStrong)

        val weaknessesList = primaryListWeak + secondListWeak
        val response: MutableList<String> = mutableListOf()
        if(weaknessesList.isNotEmpty()){
            response.addAll(weaknessesList)
            val noDamagePrimaryList = primary?.relations?.noDamageFrom?.map { it?.name } ?: emptyList()
            val noDamageSecondList = second?.relations?.noDamageFrom?.map { it?.name } ?: emptyList()
            val noDamageList = noDamagePrimaryList + noDamageSecondList
            response.removeAll(noDamageList)
        }
        view.bindWeaknesses(response)
    }

    override fun onFailure(message: String) {
        view.showFailure(message)
    }

    override fun onComplete() {
        view.hideProgressBar()
    }

}