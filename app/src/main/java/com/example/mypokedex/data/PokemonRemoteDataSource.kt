package com.example.mypokedex.data

import com.example.mypokedex.contract.HomeContract
import com.example.mypokedex.util.ProjectResources
import kotlinx.coroutines.*

class PokemonRemoteDataSource {

    private val errorMessage = "Error search data from server"

    fun findAllPokemon(presenter: HomeContract.Presenter, firstId: Int, lastId: Int) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val retrofit = HTTPData.retrofit().create(PokeAPI::class.java)
                val listId = ProjectResources.getListOfRangeId(firstId, lastId)

                val formListDeferred = listId.map {
                    async(Dispatchers.IO) { retrofit.findPokemonForm(it).execute() }
                }

                val specieListDeferred = listId.map {
                    async(Dispatchers.IO) { retrofit.findPokemonSpecie(it).execute() }
                }

                val formListResponses = formListDeferred.awaitAll()
                val specieListResponses = specieListDeferred.awaitAll()

                if (!ProjectResources.checkPokemonListResponseError(formListResponses, specieListResponses)) {
                    val formList = formListResponses.map { it.body()!! }
                    val specieList = specieListResponses.map { it.body()!! }
                    val pokemonList = ProjectResources.getPokemonList(formList, specieList)
                    presenter.onSuccess(pokemonList)
                } else {
                    throw Exception(errorMessage)
                }
            } catch (e: Exception) {
                presenter.onFailure(e.message ?: errorMessage)
            } finally {
                presenter.onComplete()
            }
        }
    }

}
