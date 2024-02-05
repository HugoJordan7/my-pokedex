package com.example.mypokedex.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.mypokedex.R
import com.example.mypokedex.contract.UnknownContract
import com.example.mypokedex.model.Pokemon
import com.example.mypokedex.presenter.UnknownPresenter
import com.example.mypokedex.util.ProjectResources
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope.coroutineContext
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

class UnknownFragment : Fragment(), UnknownContract.View {

    override lateinit var presenter: UnknownPresenter

    private lateinit var progressBar: ProgressBar
    private lateinit var imageView: ImageView
    private lateinit var unknownLayout: ConstraintLayout
    private lateinit var unknownImageLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
        presenter = UnknownPresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_unknown_pokemon, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onStart(view)
        presenter.findRandomPokemon()
    }

    override fun showPokemon(pokemon: Pokemon) {
        lifecycleScope.launch(Dispatchers.IO) {
            val bitmap = Picasso.get().load(ProjectResources.getPokeImgUrlById(pokemon.id)).get()
            withContext(Dispatchers.Main) {
                presenter.setSilhouette(imageView, bitmap)
            }
        }
    }

    override fun context() = requireContext()

    override fun showProgressBar(){
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar(){
        progressBar.visibility = View.GONE
    }

    override fun showFailure(message: String) {
        Toast.makeText(context(),message,Toast.LENGTH_SHORT).show()
    }

    override fun findAllElements(view: View){
        progressBar = view.findViewById(R.id.unknown_progress)
        imageView = view.findViewById(R.id.unknown_image)
        unknownLayout = view.findViewById(R.id.layout_unknown)
        unknownImageLayout = view.findViewById(R.id.layout_image_unknown)
    }

}