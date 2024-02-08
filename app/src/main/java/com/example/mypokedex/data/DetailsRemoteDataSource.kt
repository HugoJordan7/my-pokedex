package com.example.mypokedex.data

import com.example.mypokedex.model.Pokemon
import com.example.mypokedex.model.Type
import com.example.mypokedex.presenter.DetailsPresenter
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailsRemoteDataSource(private val presenter: DetailsPresenter) {

    private val errorMessage = "Error search data from server"

    fun findWeaknesses(primaryType: String, secondType: String?){
        val retrofit = HTTPData.retrofit().create(PokeAPI::class.java)
        GlobalScope.launch(Dispatchers.Main){
            try {
                val weakListPrimaryType: Type = withContext(Dispatchers.IO) {
                    retrofit.findWeaknessesByTypeName(primaryType).execute().body()!!
                }
                if(secondType == null){
                    presenter.onSuccessWeaknesses(weakListPrimaryType)
                }else{
                    val weakListSecondType: Type = withContext(Dispatchers.IO) {
                        retrofit.findWeaknessesByTypeName(secondType).execute().body()!!
                    }
                    presenter.onSuccessWeaknesses(weakListPrimaryType,weakListSecondType)
                }
            } catch (e: Exception){
                presenter.onFailure(e.message ?: errorMessage)
            }
        }
    }

    fun findEvolutions(url: String){
        val retrofit = HTTPData.retrofit().create(PokeAPI::class.java)
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val evolutions = async(Dispatchers.IO){
                    retrofit.findEvolutionChainByUrl(url).execute().body()!!
                }.await()
                presenter.onSuccessEvolutions(evolutions)
            }catch (e: Exception){
                presenter.onFailure(e.message ?: errorMessage)
            }
        }
    }

    fun findPokemonList(listNames: List<String>){
        val retrofit = HTTPData.retrofit().create(PokeAPI::class.java)
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val pokemonList = listNames.map { async(Dispatchers.IO){
                    retrofit.findPokemonByName(it.toLowerCase()).execute().body()!!
                } }.awaitAll()
                presenter.onSuccessPokemonEvolution(pokemonList)
            }catch (e: Exception){
                presenter.onFailure(e.message ?: errorMessage)
            }
        }
    }


}