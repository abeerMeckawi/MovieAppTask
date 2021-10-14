package com.example.movieapptask.model.pojos

import com.google.gson.annotations.SerializedName

data class Crew (

    var adult : Boolean?=null,
    var gender : Int?=null,
    var id : Int?=null,
    var known_for_department : String?=null,
    var name : String?=null,
    @SerializedName("original_name")
    var crewName : String?=null,
    var popularity : Double?=null,
    var profile_path : String?=null,
    var credit_id : String?=null,
    var department : String?=null,
    var job : String?=null,
)