package com.example.movieapptask.model.pojos

import com.google.gson.annotations.SerializedName

data class Genre (

    var id: Int = 0,
    @SerializedName("name")
    var genre: String? = null
)