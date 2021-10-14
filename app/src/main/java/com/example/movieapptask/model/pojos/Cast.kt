package com.example.movieapptask.model.pojos

import com.google.gson.annotations.SerializedName

data class Cast (

    var adult : Boolean? =null,
    var gender : Int? =null,
    var id : Int?=null,
    var known_for_department : String?=null,
    var name : String?=null,
    @SerializedName("original_name")
    var castName : String?=null,
    var popularity : Double?=null,
    @SerializedName("profile_path")
    var castImg : String?=null,
    var cast_id : Int?=null,
    var character : String?=null,
    var credit_id : String?=null,
    var order : Int?=null
)