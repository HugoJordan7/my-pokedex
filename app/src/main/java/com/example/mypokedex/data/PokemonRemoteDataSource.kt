package com.example.mypokedex.data

import com.example.mypokedex.contract.HomeContract
import com.example.mypokedex.model.Pokemon


//tenho que sair daqui com uma lista de Pokemon
class PokemonRemoteDataSource {

    fun findPokemonForm(presenter: HomeContract.Presenter,pokemon: Pokemon){
        HTTPData.retrofit()
            .create(PokeAPI::class.java)
            .findPokemonForm(pokemon.id)
    }

    fun findPokemonSpecie(presenter: HomeContract.Presenter,pokemon: Pokemon){
        HTTPData.retrofit()
            .create(PokeAPI::class.java)
            .findPokemonSpecie(pokemon.id)
    }
}