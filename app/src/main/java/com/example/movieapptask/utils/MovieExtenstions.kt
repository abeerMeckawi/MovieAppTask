package com.example.movieapptask.utils

import android.view.View
import android.widget.SearchView
import com.example.movieapptask.model.pojos.HandleDataStatus
import com.example.movieapptask.view.fragments.MoviesFragment
import com.google.android.material.snackbar.Snackbar


fun MoviesFragment.handleDataStatus() = object : HandleDataStatus{

    override fun onLoading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.tvNoResutlt.visibility= View.INVISIBLE
    }
    override fun onSuccess() {
        binding.progressBar.visibility = View.INVISIBLE
        binding.tvNoResutlt.visibility= View.INVISIBLE
    }
    override fun onFailed() {
        binding.progressBar.visibility = View.INVISIBLE
        Snackbar.make(binding.root.rootView,"Not Data Found", Snackbar.LENGTH_SHORT).show()
    }
}

fun MoviesFragment.handleSearch(){

    binding.searchBarView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(p0: String?): Boolean {
            p0?.let { searchVM.getSearchMovieResponse(it.trim())}
            binding.searchBarView.clearFocus()
            return true
        }
        override fun onQueryTextChange(p0: String?): Boolean {
            return true
        }
    })
}

