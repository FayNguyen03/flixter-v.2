package com.example.and102_week4

import com.google.gson.annotations.SerializedName

class NowPlayingMovie {
    @JvmField
    @SerializedName("original_title")
    var nameMovie: String? = null

    @JvmField
    @SerializedName("overview")
    var summary: String? = null

    @SerializedName("poster_path")
    var movieUrl: String? = null

    @SerializedName("release_date")
    var date: String? = null

    @SerializedName("vote_average")
    var vote:Double?=null
}