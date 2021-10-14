package com.example.movieapptask.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapptask.model.network.APIClient
import com.example.movieapptask.model.pojos.DataStatus
import com.example.movieapptask.model.pojos.Movie
import com.example.movieapptask.model.pojos.SearchMovieResponse
import retrofit2.Call
import retrofit2.Response

class SearchMovieVM : ViewModel() {

    private var movie: Movie = Movie()
    private var searchList: ArrayList<Movie> = ArrayList()
    private val moviesList: ArrayList<Movie> = ArrayList()
    val movieList: MutableLiveData<ArrayList<Movie>> by lazy { MutableLiveData() }
    val dataStatus: MutableLiveData<DataStatus> by lazy { MutableLiveData() }

    fun getSearchMovieResponse(searchMovieTxt: String) {
        moviesList.clear()
        dataStatus.postValue(DataStatus.LOADING)
        APIClient.getSearchService()?.getMoviesIDs(searchMovieTxt)
            ?.enqueue(object : retrofit2.Callback<SearchMovieResponse?> {
                override fun onResponse(
                    call: Call<SearchMovieResponse?>,
                    response: Response<SearchMovieResponse?>
                ) {
                    searchList = response.body()?.movies!!
                    for (item in searchList) {
                        if (item.id != null) {
                            getMovies(item.id!!)
                        }
                    }
                }
                override fun onFailure(call: Call<SearchMovieResponse?>, t: Throwable) {
                    dataStatus.postValue(DataStatus.FAIL)
                }
            })
    }


    private fun getMovies(id: Int) {
        APIClient.getMovieService()?.getMovie(id)?.enqueue(object : retrofit2.Callback<Movie> {
            override fun onResponse(
                call: Call<Movie?>,
                response: Response<Movie?>
            ) {
                movie = response.body()!!
                moviesList.add(movie)
                if (moviesList.count() == searchList.count()) {
                    movieList.postValue(moviesList)
                    dataStatus.postValue(DataStatus.SUCCESS)
                }
            }
            override fun onFailure(call: Call<Movie?>, t: Throwable) {
                dataStatus.postValue(DataStatus.FAIL)
            }
        })
    }

}

