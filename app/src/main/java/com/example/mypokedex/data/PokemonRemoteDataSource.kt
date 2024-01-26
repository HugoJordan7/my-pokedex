package com.example.mypokedex.data

import android.util.Log
import com.example.mypokedex.R
import com.example.mypokedex.contract.HomeContract
import com.example.mypokedex.model.PokemonForm
import com.example.mypokedex.model.PokemonSpecie
import com.example.mypokedex.util.ProjectResources
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PokemonRemoteDataSource {

    private val errorMessage = "Error search data from server"

    fun findAllPokemon(presenter: HomeContract.Presenter, firstId: Int, lastId: Int) {
        GlobalScope.launch(Dispatchers.Main){
            try {
                val retrofit = HTTPData.retrofit().create(PokeAPI::class.java)
                val listId = ProjectResources.getListOfPokemonId(firstId, lastId)

                val formListDeferred = async(Dispatchers.IO) {
                    listId.map { retrofit.findPokemonForm(it).execute() }
                }
                val specieListDeferred = async(Dispatchers.IO) {
                    listId.map { retrofit.findPokemonSpecie(it).execute() }
                }

                if(!ProjectResources.checkPokemonListResponseError(formListDeferred.await(), specieListDeferred.await())){
                    val formList = formListDeferred.await().map { it.body()!! }
                    val specieList = specieListDeferred.await().map { it.body()!! }
                    val pokemonList = ProjectResources.getPokemonList(formList,specieList)
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
