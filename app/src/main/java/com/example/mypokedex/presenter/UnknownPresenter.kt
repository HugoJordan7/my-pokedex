package com.example.mypokedex.presenter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import com.example.mypokedex.R
import com.example.mypokedex.contract.UnknownContract
import com.example.mypokedex.data.UnknownRemoteDataSource
import com.example.mypokedex.model.Pokemon
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
        this.view.showProgressBar()
    }

    override fun findRandomPokemon() {
        val id = Random.nextInt(1,100)
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