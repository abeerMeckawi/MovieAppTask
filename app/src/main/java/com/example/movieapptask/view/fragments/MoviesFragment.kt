package com.example.movieapptask.view.fragments


import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.movieapptask.R
import com.example.movieapptask.databinding.FragmentMoviesBinding
import com.example.movieapptask.model.pojos.DataStatus.*
import com.example.movieapptask.utils.handleDataStatus
import com.example.movieapptask.view.activities.MainActivity
import com.example.movieapptask.view.adapter.MovieAdapter
import com.example.movieapptask.viewmodel.SearchMovieVM

class MoviesFragment : Fragment() {

    internal lateinit var binding: FragmentMoviesBinding
    private val searchVM :SearchMovieVM by lazy { (requireActivity() as MainActivity).searchVM }
    private val movieAdapter: MovieAdapter by lazy { MovieAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        binding.rvMovie.adapter = movieAdapter

        searchVM.movieList.observe(viewLifecycleOwner, {
            movieAdapter.setData(it)
            handleDataStatus().onSuccess()
        })

        searchVM.dataStatus.observe(viewLifecycleOwner,{
          when(it){
              LOADING -> handleDataStatus().onLoading()
              SUCCESS ->handleDataStatus().onSuccess()
              FAIL -> handleDataStatus().onFailed()
              else -> return@observe
          }
        })

        setHasOptionsMenu(true)
        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val search: MenuItem = menu.findItem(R.id.search)
        val searchView: SearchView = search.actionView as SearchView
        searchView.queryHint = "Search For Movie"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                p0?.let { searchVM.getSearchMovieResponse(it.trim())}
                searchView.clearFocus()
                return true
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }
        })
    }

}