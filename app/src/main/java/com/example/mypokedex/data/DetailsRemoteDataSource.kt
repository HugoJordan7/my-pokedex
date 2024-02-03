package com.example.mypokedex.data

import com.example.mypokedex.model.Type
import com.example.mypokedex.presenter.DetailsPresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
            } finally {
                presenter.onComplete()
            }
        }
    }

}