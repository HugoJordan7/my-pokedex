package com.example.mypokedex.data

import com.example.mypokedex.presenter.UnknownPresenter
import kotlinx.coroutines.*

class UnknownRemoteDataSource {
    fun findRandomPokemonById(presenter: UnknownPresenter,id: Int){
        val retrofit = HTTPData.retrofit().create(PokeAPI::class.java)
        GlobalScope.launch(Dispatchers.Main){
            try {
                val randomPokemon = withContext(Dispatchers.IO){
                    retrofit.findPokemonById(id).execute().body()!!
                }
                presenter.onSuccess(randomPokemon)
            } catch (e: Exception) {
                presenter.onFailure(e.message ?: "Error search data from server")
            }
        }
    }
}