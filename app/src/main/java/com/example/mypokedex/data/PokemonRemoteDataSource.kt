package com.example.mypokedex.data

import com.example.mypokedex.contract.HomeContract
import com.example.mypokedex.util.ProjectResources
import kotlinx.coroutines.*

class PokemonRemoteDataSource {

    fun findAllPokemon(presenter: HomeContract.Presenter, firstId: Int, lastId: Int) {
        val retrofit = HTTPData.retrofit().create(PokeAPI::class.java)
        val listId = ProjectResources.getListOfRangeId(firstId, lastId)
        val chunks = listId.chunked(5)
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val deferred = chunks.map { chunk ->
                    async(Dispatchers.IO) {
                        chunk.map{
                            val pokemon = retrofit.findPokemonById(it).execute().body()!!
                            pokemon.specie =
                                retrofit.findPokemonSpecieById(pokemon.id).execute().body()!!
                            pokemon
                        }
                    }
                }
                val pokemonList = deferred.awaitAll().flatten()
                presenter.onSuccess(pokemonList)
            } catch (e: Exception) {
                presenter.onFailure(e.message ?: "Error search data from server")
            } finally {
                presenter.onComplete()
            }
        }
    }

}
