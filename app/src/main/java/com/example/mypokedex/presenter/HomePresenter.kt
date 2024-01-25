package com.example.mypokedex.presenter

import android.os.Handler
import android.os.Looper
import android.view.View
import com.example.mypokedex.contract.HomeContract
import com.example.mypokedex.model.Pokemon
import com.example.mypokedex.util.ProjectResources
import com.example.mypokedex.view.PokemonItem

class HomePresenter(override var view: HomeContract.View) : HomeContract.Presenter {

    override fun onStart(view: View) {
        this.view.bindAllViews(view)
        this.view.showProgressBar()
    }

    override fun findAllPokemon() {
        Handler(Looper.getMainLooper()).postDelayed(
            {
                var list = ProjectResources.getMutableListPokemonTest()
                onSuccess(list)
            }, 2000
        )
    }

    override fun onSuccess(response: List<Pokemon>) {
        var listItem = response.map { PokemonItem(it, view.context()) }
        view.showPokemon(listItem)
        onComplete()
    }

    override fun onFailure(message: String) {
        view.showFailure(message)
        onComplete()
    }

    override fun onComplete() {
        view.hideProgressBar()
    }

}

/*
            Pokemon(
                id = 2,
                name = "ivysaur",
                iconUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/2.png",
                primaryType = "grass",
                secondType = "poison",
                color = "green"
            ),
            Pokemon(
                id = 150,
                name = "charizard",
                iconUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/6.png",
                primaryType = "fire",
                secondType = "flying",
                color = "red"
            ),
            Pokemon(
                id = 93,
                name = "haunter",
                iconUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/93.png",
                primaryType = "ghost",
                secondType = "poison",
                color = "purple"
            ),
            Pokemon(
                id = 9999,
                name = "blastoise",
                iconUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/9.png",
                primaryType = "water",
                secondType = "normal",
                color = "blue"
            ),
            Pokemon(
                id = 2,
                name = "ivysaur",
                iconUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/2.png",
                primaryType = "grass",
                secondType = "poison",
                color = "yellow"
            ),
            Pokemon(
                id = 150,
                name = "charizard",
                iconUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/6.png",
                primaryType = "fire",
                secondType = "flying",
                color = "white"
            ),
            Pokemon(
                id = 9999,
                name = "blastoise",
                iconUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/9.png",
                primaryType = "water",
                color = "pink"
            )*/