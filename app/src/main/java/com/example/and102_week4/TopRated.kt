package com.example.and102_week4
import com.google.gson.annotations.SerializedName
class TopRated {
    @JvmField
    @SerializedName("original_title")
    var nameMovie: String? = null

    @JvmField
    @SerializedName("overview")
    var summary: String? = null

    @SerializedName("poster_path")
    var movieUrl: String? = null

    @SerializedName("first_air_date")
    var date: String? = null

    @SerializedName("vote_average")
    var vote:Double?=null

    @SerializedName("origin_country")
    var country:String?=null
}