package com.example.movieapptask.model.pojos

import com.google.gson.annotations.SerializedName

data class Movie(
    var adult: Boolean?=null,
    @SerializedName("backdrop_path")
    var imgBG: String?=null,
    var budget: Int?=null,
    var genres: List<Genre>?=null,
    var homepage: String?=null,
    var id: Int?=null,
    var imdb_id: String?=null,
    var original_language: String?=null,
    @SerializedName("original_title")
    var movieTitle: String?=null,
    @SerializedName("overview")
    var about: String?=null,
    var popularity: Double?=null,
    @SerializedName("poster_path")
    var imgPath: String?=null,
    @SerializedName("release_date")
    var date: String?=null,
    var revenue: Int?=null,
    @SerializedName("runtime")
    var duration: Int?=null,
    var status: String?=null,
    var tagline: String?=null,
    var title: String?=null,
    var video: Boolean?=null,
    @SerializedName("vote_average")
    var rate: Double?=null,
    var vote_count: Int?=null,
    var videos: Video?=null,
    var credits: Credit?=null,
)