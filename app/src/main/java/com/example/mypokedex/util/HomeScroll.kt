package com.example.mypokedex.util

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mypokedex.contract.HomeContract

object HomeScroll {

    const val RANGE = 100

    fun getScroll(presenter: HomeContract.Presenter) = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val lastItem = layoutManager.findLastCompletelyVisibleItemPosition()
            val totalItemCount = layoutManager.itemCount
            if (lastItem == totalItemCount - 1  && totalItemCount < 1000) {
                presenter.loadMorePokemon(lastItem + 2)
            }
        }
    }
}