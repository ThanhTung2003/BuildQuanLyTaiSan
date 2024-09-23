package com.example.quanlytaisanbt2.search

import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quanlytaisanbt2.category.DataCategory
import com.example.quanlytaisanbt2.movie.DataMovie
import java.util.Locale

class SearchHandler(
    private val searchView: SearchView,
    private val categoryList: List<DataCategory>,
    private val recyclerView: RecyclerView,
    private val defaultAdapter: RecyclerView.Adapter<*>
) {

    private var searchList = arrayListOf<DataMovie>()

    fun setupSearch() {
        searchView.clearFocus()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchList.clear()
                val searchText = newText?.toLowerCase(Locale.getDefault()) ?: ""
                if (searchText.isNotEmpty()) {
                    categoryList.forEach { category ->
                        category.listMovies.forEach { movie ->
                            if (movie.dataTitleMovie.toLowerCase(Locale.getDefault()).contains(searchText)) {
                                searchList.add(movie)
                            }
                        }
                    }
                    val searchAdapter = SearchAdapter(searchList)
                    recyclerView.layoutManager = LinearLayoutManager(recyclerView.context, LinearLayoutManager.HORIZONTAL, false)
                    recyclerView.adapter = searchAdapter
                } else {
                    recyclerView.adapter = defaultAdapter
                }
                return true
            }
        })
    }
}
