package com.example.movieapptask.model.network

import com.example.movieapptask.BuildConfig
import com.example.movieapptask.model.pojos.Movie
import com.example.movieapptask.model.pojos.SearchMovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {

    @GET("3/search/movie")
    fun getMoviesIDs(
        @Query("query") movieSearchName: String,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
    ): Call<SearchMovieResponse>

    @GET("3/movie/{id}")
    fun getMovie(
        @Path("id") movieId: Int,
        @Query("api_key") apiKey: String =  BuildConfig.API_KEY
    ): Call<Movie>

    @GET("3/movie/{id}")
    fun getMovieDetails(
        @Path("id") movieId: Int,
        @Query("append_to_response") responseAppend : String,
        @Query("api_key") apiKey: String =  BuildConfig.API_KEY
    ): Call<Movie>

}