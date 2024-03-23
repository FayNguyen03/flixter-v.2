package com.example.and102_week4
import android.support.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
class TopRated: java.io.Serializable {
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