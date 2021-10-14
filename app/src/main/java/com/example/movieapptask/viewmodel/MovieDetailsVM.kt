package com.example.movieapptask.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapptask.model.network.APIClient
import com.example.movieapptask.model.pojos.DataStatus
import com.example.movieapptask.model.pojos.Movie
import com.example.movieapptask.model.pojos.VideoDetails
import retrofit2.Call
import retrofit2.Response

class MovieDetailsVM : ViewModel() {


    val movie: MutableLiveData<Movie> by lazy { MutableLiveData() }
    val video: MutableLiveData<VideoDetails> by lazy { MutableLiveData() }
    val dataStatus: MutableLiveData<DataStatus> by lazy { MutableLiveData() }


    fun getMovieDetails(movieId: Int) {
        dataStatus.postValue(DataStatus.LOADING)
        APIClient.getMovieDetailsService()?.getMovieDetails(movieId,"videos,credits")
            ?.enqueue(object : retrofit2.Callback<Movie> {
                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                    if (response.body() != null) {
                        movie.postValue(response.body())
                        val videoData = response.body()!!.videos?.results

                        if (videoData != null && videoData.count() > 0)
                            video.postValue(videoData.get(0))

                        dataStatus.postValue(DataStatus.SUCCESS)
                    }
                }
                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    dataStatus.postValue(DataStatus.FAIL)
                }
            })
    }
}