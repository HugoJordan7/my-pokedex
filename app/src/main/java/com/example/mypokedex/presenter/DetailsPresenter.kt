package com.example.mypokedex.presenter

import android.util.Log
import android.view.View
import com.example.mypokedex.R
import com.example.mypokedex.contract.DetailsContract
import com.example.mypokedex.data.DetailsRemoteDataSource
import com.example.mypokedex.model.*
import com.example.mypokedex.util.ProjectResources
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

    fun getDescriptionText(descriptionList: List<Description>): String{
        if(descriptionList.isEmpty()) return view.getString(R.string.unknown)
        for (desc in descriptionList){
            if(desc.language.name == "es" && desc.version.name == "omega-ruby"){
                return desc.text
            }
        }
        for (desc in descriptionList){
            if(desc.language.name == "es"){
                return desc.text
            }
        }
        for (desc in descriptionList){
            if(desc.language.name == "en"){
                return desc.text
            }
        }
        return descriptionList[0].text
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

    fun findEvolutions(pokemon: Pokemon){
        val evolutionsUrl = pokemon.specie?.evolutionChain?.url!!
        dataSource.findEvolutions(evolutionsUrl)
    }

    fun onSuccessEvolutions(evolutions: Evolutions){
        val listEvolutions: MutableList<String> = mutableListOf()
        listEvolutions.add(evolutions.chain.species.name) //First Chain Evolution
        val secondChain = evolutions.chain.evolvesTo
        if(secondChain.isNotEmpty()){
            for(chain2 in secondChain){ //Second chain evolution
                listEvolutions.add(chain2.species.name)
                if (chain2.evolvesTo.isNotEmpty()){
                    for(chain3 in chain2.evolvesTo){ //Three chain evolution
                        listEvolutions.add(chain3.species.name)
                    }
                }
            }
        }

        if (listEvolutions.size > 1) dataSource.findPokemonList(listEvolutions)
        else {
            view.bindEvolutions(emptyList(),1)
            onComplete()
        }
    }

    fun onSuccessPokemonEvolution(response: List<Pokemon>){
        val listEvolutionItem = response.map { EvolutionItem(
            it.name, it.id, ProjectResources.getPokeImgUrlById(it.id)
        ) }
        val columns = if(listEvolutionItem.size == 2) 2 else 3
        view.bindEvolutions(listEvolutionItem,columns)
        onComplete()
    }


    override fun onFailure(message: String) {
        view.showFailure(message)
    }

    override fun onComplete() {
        view.hideProgressBar()
    }

}