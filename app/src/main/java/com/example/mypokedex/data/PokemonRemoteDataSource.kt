package com.example.mypokedex.data

import com.example.mypokedex.contract.HomeContract
import com.example.mypokedex.model.Pokemon
import com.example.mypokedex.util.ProjectResources
import kotlinx.coroutines.*
import retrofit2.Response

class PokemonRemoteDataSource {

    fun findAllPokemon(presenter: HomeContract.Presenter, firstId: Int, lastId: Int) {
        val retrofit = HTTPData.retrofit().create(PokeAPI::class.java)
        val listId = ProjectResources.getListOfRangeId(firstId, lastId)
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val pokemonList = listId.map { async(Dispatchers.IO) {
                    Pokemon(
                        retrofit.findPokemonForm(it).execute().body()!!,
                        retrofit.findPokemonSpecie(it).execute().body()!!
                    )
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


/*  Old form:
                val formListDeferred = listId.map {
                    async(Dispatchers.IO) { retrofit.findPokemonForm(it).execute() }
                }

                val specieListDeferred = listId.map {
                    async(Dispatchers.IO) { retrofit.findPokemonSpecie(it).execute() }
                }
                val formListResponses = formListDeferred.awaitAll()
                val specieListResponses = specieListDeferred.awaitAll()
                val formList = formListResponses.map { it.body()!! }
                val specieList = specieListResponses.map { it.body()!! }
                val pokemonList = ProjectResources.getPokemonList(formList, specieList)*/
