package com.example.mypokedex.view

import android.os.Bundle
import android.text.InputFilter
import android.view.*
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mypokedex.R

class SearchFragment: Fragment() {

    private lateinit var searchBar: View
    private lateinit var searchEditText: EditText
    private lateinit var searchCloseButton: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        val searchItem = menu.findItem(R.id.item_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(requireContext(), "search pokemon", Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return true
            }
        })
        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                //Actions when searchItem is open
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                //Actions when searchItem is close
                findNavController().popBackStack()
                return true
            }

        })
        customizingActionBar(searchView)
        searchItem.expandActionView()
    }

    private fun customizingActionBar(searchView: SearchView) {
        bindAllSearchViewElements(searchView)
        searchEditText.apply {
            setHint(R.string.search)
            textAlignment = View.TEXT_ALIGNMENT_CENTER
            setSingleLine()
            filters = arrayOf<InputFilter>(InputFilter.LengthFilter(15))
        }
        searchBar.setBackgroundResource(R.drawable.search_view_shape)
        searchBar.layoutParams = (searchBar.layoutParams as ViewGroup.MarginLayoutParams).apply {
            topMargin = 15
            bottomMargin = 15
            rightMargin = 40
        }
    }

    private fun bindAllSearchViewElements(searchView: SearchView) {
        searchEditText = searchView.findViewById(androidx.appcompat.R.id.search_src_text)
        searchBar = searchView.findViewById(androidx.appcompat.R.id.search_bar)
        searchCloseButton = searchView.findViewById(androidx.appcompat.R.id.search_close_btn)
    }


}