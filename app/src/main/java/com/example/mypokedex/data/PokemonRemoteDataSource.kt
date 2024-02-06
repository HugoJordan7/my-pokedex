package com.example.mypokedex.data

import com.example.mypokedex.contract.HomeContract
import com.example.mypokedex.util.ProjectResources
import kotlinx.coroutines.*

class PokemonRemoteDataSource {

    fun findAllPokemon(presenter: HomeContract.Presenter, firstId: Int, lastId: Int) {
        val retrofit = HTTPData.retrofit().create(PokeAPI::class.java)
        val listId = ProjectResources.getListOfRangeId(firstId, lastId)
        val chunks = listId.chunked(20)
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val pokemonList = chunks.flatMap { chunk ->
                    chunk.map { async(Dispatchers.IO) {
                        val pokemon = retrofit.findPokemonById(it).execute().body()!!
                        pokemon.specie =
                            retrofit.findPokemonSpecieById(pokemon.id).execute().body()!!
                        pokemon
                    } }.awaitAll()
                }
                presenter.onSuccess(pokemonList)
            } catch (e: Exception) {
                presenter.onFailure(e.message ?: "Error search data from server")
            } finally {
                presenter.onComplete()
            }
        }
    }

}
