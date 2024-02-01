package com.example.mypokedex.data

import android.util.Log
import com.example.mypokedex.contract.SearchContract
import kotlinx.coroutines.*

class SearchPokemonRemoteDataSource {
    fun findAllPokemonByName(query: String, presenter: SearchContract.Presenter) {
        val retrofit = HTTPData.retrofit().create(PokeAPI::class.java)
        GlobalScope.launch(Dispatchers.Main) {
            try {

                val filteredNamesList = withContext(Dispatchers.IO) {
                    retrofit.findPokemonNamesList().execute().body()!!
                }.results.filter { it.name.contains(query) }

                val pokemonList = filteredNamesList.map { async(Dispatchers.IO){
                    val pokemon = retrofit.findPokemonByName(it.name).execute().body()!!
                    pokemon.specie = retrofit.findPokemonSpecieById(pokemon.id).execute().body()!!
                    pokemon
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
