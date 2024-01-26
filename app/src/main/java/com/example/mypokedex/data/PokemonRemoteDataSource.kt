package com.example.mypokedex.data

import com.example.mypokedex.contract.HomeContract
import com.example.mypokedex.model.PokemonForm
import com.example.mypokedex.model.PokemonSpecie
import com.example.mypokedex.util.ProjectResources
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonRemoteDataSource {

    fun findAllPokemon(presenter: HomeContract.Presenter,firstId: Int, lastId: Int){
        var retrofit = HTTPData.retrofit().create(PokeAPI::class.java)
        var idList = ProjectResources.getListOfPokemonId(firstId,lastId)
        var pokemonItemsList = idList.map { retrofit.findPokemonForm(it) to retrofit.findPokemonSpecie(it) }
        var pokemonFormList: MutableList<PokemonForm> = mutableListOf()
        var pokemonSpecieList: MutableList<PokemonSpecie> = mutableListOf()
        pokemonItemsList.forEach { (pokeForm, pokeSpecie) ->
            pokeForm.enqueue(object : Callback<PokemonForm>{
                override fun onResponse(call: Call<PokemonForm>, response: Response<PokemonForm>) {
                    if(response.isSuccessful || response.body() != null){
                        pokemonFormList.add(response.body()!!)
                    }else{
                        presenter.onFailure(response.errorBody()?.string() ?: "Error search data from server")
                        presenter.onComplete()
                    }

                }

                override fun onFailure(call: Call<PokemonForm>, t: Throwable) {
                    presenter.onFailure(t.message ?: "Error search data from server")
                    presenter.onComplete()
                }

            })
            pokeSpecie.enqueue(object : Callback<PokemonSpecie>{
                override fun onResponse(call: Call<PokemonSpecie>, response: Response<PokemonSpecie>) {
                    if(response.isSuccessful || response.body() != null){
                        pokemonSpecieList.add(response.body()!!)
                    }else{
                        presenter.onFailure(response.errorBody()?.string() ?: "Error search data from server")
                        presenter.onComplete()
                    }
                }

                override fun onFailure(call: Call<PokemonSpecie>, t: Throwable) {
                    presenter.onFailure(t.message ?: "Error search data from server")
                    presenter.onComplete()
                }
            })
        }
        var resultList = ProjectResources.mergeItemsOfPokemonList(
            pokemonFormList,
            pokemonSpecieList
        )
        if(resultList.isEmpty()){
            presenter.onFailure("Error search data from server")
        } else{
            presenter.onSuccess(resultList)
        }
        presenter.onComplete()
    }
}