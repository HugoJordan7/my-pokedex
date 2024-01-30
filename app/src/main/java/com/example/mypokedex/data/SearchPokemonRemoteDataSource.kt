package com.example.mypokedex.data

import android.util.Log
import com.example.mypokedex.contract.SearchContract
import com.example.mypokedex.model.FewPokeDetails
import com.example.mypokedex.model.Pokemon
import com.example.mypokedex.util.ProjectResources
import kotlinx.coroutines.*
import retrofit2.await

class SearchPokemonRemoteDataSource {
    fun findAllPokemonByName(query: String, presenter: SearchContract.Presenter) {
        val retrofit = HTTPData.retrofit().create(PokeAPI::class.java)
        val idList = ProjectResources.getListOfRangeId(1,10)
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val pokeNames: List<FewPokeDetails> = idList.map { async(Dispatchers.IO){
                    retrofit.findFewPokemonDetails(it).execute().body()!!
                } }.awaitAll()
                val filteredNames: List<FewPokeDetails> = pokeNames.filter { it.name.toLowerCase().contains(query.toLowerCase()) }
                val pokemonList: List<Pokemon> = filteredNames.map { async(Dispatchers.IO){
                    Pokemon(
                        retrofit.findPokemonForm(it.id).execute().body()!!,
                        retrofit.findPokemonSpecie(it.id).execute().body()!!
                    )
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