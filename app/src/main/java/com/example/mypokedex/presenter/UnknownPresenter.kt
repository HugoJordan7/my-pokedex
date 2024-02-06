package com.example.mypokedex.presenter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import com.example.mypokedex.R
import com.example.mypokedex.contract.UnknownContract
import com.example.mypokedex.data.UnknownRemoteDataSource
import com.example.mypokedex.model.Pokemon
import com.example.mypokedex.model.PokemonNamesList
import com.example.mypokedex.view.UnknownFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

class UnknownPresenter(
    override var view: UnknownFragment,
    override var dataSource: UnknownRemoteDataSource = UnknownRemoteDataSource()
) : UnknownContract.Presenter {

    override fun onStart(view: View) {
        this.view.findAllElements(view)
    }

    override fun findRandomPokemon() {
        view.showProgressBar()
        val id = Random.nextInt(1,300)
        dataSource.findRandomPokemonById(this,id)
    }

    fun setSilhouette(imageView: ImageView, bitmap: Bitmap){
        view.lifecycleScope.launch(Dispatchers.Default) {
            val silhouette = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
            for (x in 0 until bitmap.width) {
                for (y in 0 until bitmap.height) {
                    val pixel = bitmap.getPixel(x, y)
                    if (pixel != Color.TRANSPARENT) {
                        silhouette.setPixel(x, y, Color.BLACK)
                    }
                }
            }
            withContext(Dispatchers.Main){
                imageView.setImageBitmap(silhouette)
                imageView.visibility = View.VISIBLE
                view.hideProgressBar()
            }
        }
    }

    override fun checkResult(autoComplete: AutoCompleteTextView, button: Button, pokemon: Pokemon){
        if (autoComplete.text.isEmpty()){
            view.showFailure(view.getString(R.string.empty_edit_text_unknown))
            return
        }
        if(autoComplete.text.toString().toLowerCase() == pokemon.name){
            view.showResult(view.getString(R.string.unknown_success))
        } else{
            view.showResult(view.getString(R.string.unknown_error))
        }
        autoComplete.isEnabled = false
        button.isClickable = false
    }

    override fun getPokemonNamesList() {
        dataSource.findPokemonNamesList(this,1000)
    }

    override fun onSuccessPokeList(pokemonNamesList: PokemonNamesList) {
        view.setAutoCompleteAdapter(pokemonNamesList.results.map { it.name })
    }

    override fun onSuccess(pokemon: Pokemon) {
        view.showPokemon(pokemon)
    }

    override fun onFailure(message: String) {
        view.showFailure(message)
    }

    override fun onComplete(){
        view.hideProgressBar()
    }

}