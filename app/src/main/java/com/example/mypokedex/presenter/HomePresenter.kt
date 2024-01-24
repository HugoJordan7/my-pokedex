package com.example.mypokedex.presenter

import android.view.View
import com.example.mypokedex.contract.HomeContract
import com.example.mypokedex.model.Pokemon
import com.example.mypokedex.view.PokemonItem

class HomePresenter(override var view: HomeContract.View) : HomeContract.Presenter {

    override fun onStart(view: View) {
        this.view.bindAllViews(view)
    }

    override fun findAllPokemon() {
        var list = mutableListOf(
            Pokemon(
                id = 2,
                name = "ivysaur",
                iconUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/2.png",
                primaryType = "grass",
                secondType = "poison"
            ),
            Pokemon(
                id = 150,
                name = "charizard",
                iconUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/6.png",
                primaryType = "fire",
                secondType = "flying"
            ),
            Pokemon(
                id = 9999,
                name = "blastoise",
                iconUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/9.png",
                primaryType = "water",
                secondType = "normal"
            )
        )
        var listItem = list.map { PokemonItem(it) }

        view.showPokemon(listItem)
    }

}