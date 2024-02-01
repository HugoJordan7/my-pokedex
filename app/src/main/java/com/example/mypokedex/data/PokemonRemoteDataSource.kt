package com.example.mypokedex.data

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
                    retrofit.findPokemonById(it).execute().body()!!
                }}.awaitAll()
                pokemonList.map { async(Dispatchers.IO){
                    it.specie = retrofit.findPokemonSpecieById(it.id).execute().body()!!
                    it.iconUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/${it.id}.png"
                } }
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
