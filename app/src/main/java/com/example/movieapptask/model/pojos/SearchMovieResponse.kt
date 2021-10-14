package com.example.movieapptask.model.pojos

import com.google.gson.annotations.SerializedName

data class SearchMovieResponse(

    var page: Int = 1,
    @SerializedName("results")
    var movies: ArrayList<Movie>,
    @SerializedName("total_pages")
    var totalPages: Int? = null,
    @SerializedName("total_results")
    var totalMovies: Int? = null
)