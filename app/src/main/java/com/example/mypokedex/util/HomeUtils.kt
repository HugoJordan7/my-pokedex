package com.example.mypokedex.util

import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mypokedex.contract.HomeContract
import com.xwray.groupie.GroupieAdapter

object HomeUtils {

    val adapter = GroupieAdapter()

    const val RANGE = 5

    fun getScroll(presenter: HomeContract.Presenter, progressBar: ProgressBar) = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val lastItem = layoutManager.findLastCompletelyVisibleItemPosition()
            val totalItemCount = layoutManager.itemCount
            if (lastItem == totalItemCount - 1  && totalItemCount < 1000 && !progressBar.isVisible) {
                presenter.loadMorePokemon(lastItem+2)
            }
        }
    }
}