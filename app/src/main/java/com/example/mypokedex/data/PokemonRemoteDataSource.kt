package com.example.mypokedex.data

import android.util.Log
import com.example.mypokedex.contract.HomeContract
import com.example.mypokedex.model.Pokemon
import com.example.mypokedex.util.ProjectResources
import kotlinx.coroutines.*
import retrofit2.Response
import retrofit2.await

class PokemonRemoteDataSource {

    fun findAllPokemon(presenter: HomeContract.Presenter, firstId: Int, lastId: Int) {
        val retrofit = HTTPData.retrofit().create(PokeAPI::class.java)
        val listId = ProjectResources.getListOfRangeId(firstId, lastId)
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val pokemonList = listId.map { async(Dispatchers.IO) {
                    val pokemon = retrofit.findPokemonById(it).execute().body()!!
                    pokemon.specie = retrofit.findPokemonSpecieById(pokemon.id).execute().body()!!
                    pokemon
                }}.awaitAll()
                presenter.onSuccess(pokemonList)
            } catch (e: Exception) {
                presenter.onFailure(e.message ?: "Error search data from server")
            } finally {
                presenter.onComplete()
            }
        }
    }

}
