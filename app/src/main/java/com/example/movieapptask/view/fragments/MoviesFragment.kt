package com.example.movieapptask.view.fragments


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.movieapptask.databinding.FragmentMoviesBinding
import com.example.movieapptask.model.pojos.DataStatus.*
import com.example.movieapptask.utils.handleDataStatus
import com.example.movieapptask.utils.handleSearch
import com.example.movieapptask.view.activities.MainActivity
import com.example.movieapptask.view.adapter.MovieAdapter
import com.example.movieapptask.viewmodel.SearchMovieVM

class MoviesFragment : Fragment() {

    internal lateinit var binding: FragmentMoviesBinding
    internal val searchVM :SearchMovieVM by lazy { (requireActivity() as MainActivity).searchVM }
    private val movieAdapter: MovieAdapter by lazy { MovieAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        handleSearch()
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

        return binding.root
    }
}