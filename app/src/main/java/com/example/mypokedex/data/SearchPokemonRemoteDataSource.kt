package com.example.mypokedex.data

import com.example.mypokedex.contract.SearchContract
import com.example.mypokedex.model.Pokemon
import com.example.mypokedex.util.ProjectResources
import kotlinx.coroutines.*

class SearchPokemonRemoteDataSource {
    fun findAllPokemonByName(query: String, presenter: SearchContract.Presenter) {
        val retrofit = HTTPData.retrofit().create(PokeAPI::class.java)
        val idList = ProjectResources.getListOfRangeId(1,1000)
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val pokeFormListFiltered = idList.map { async(Dispatchers.IO){
                    retrofit.findPokemonForm(it).execute().body()!!
                } }.awaitAll().filter { it.name.toLowerCase().contains(query.toLowerCase()) }

                val pokemonList = pokeFormListFiltered.map { async(Dispatchers.IO){
                    Pokemon(it, retrofit.findPokemonSpecie(it.id).execute().body()!!)
                } }.awaitAll()
                presenter.onSuccess(pokemonList)
            } catch (e: Exception) {
                presenter.onFailure(e.message ?: "Error search data from server")
            } finally {
                presenter.onComplete()
            }
        }
    }
}
