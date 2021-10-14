package com.example.movieapptask.model.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {

    private const val BASE_URL = "https://api.themoviedb.org"
    const val BASE_IMG_URL = "https://image.tmdb.org/t/p/original/"

    private var retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getSearchService(): MovieAPI? {
        return retrofit.create(MovieAPI::class.java)
    }

    fun getMovieService():MovieAPI?{
        return retrofit.create(MovieAPI::class.java)
    }

    fun getMovieDetailsService():MovieAPI?{
        return retrofit.create(MovieAPI::class.java)
    }

}