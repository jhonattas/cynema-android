package br.com.patrocine.patrocine.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.ArrayList

class Movie: Serializable {

    @SerializedName("id")
    var id: Int = 0
    @SerializedName("title")
    var title: String? = null
    @SerializedName("full_title")
    var fullTitle: String? = null
    @SerializedName("sinopse")
    var sinopse: String? = null
    @SerializedName("duration")
    var duration: Int = 0
    @SerializedName("genre")
    var genre: String? = null
    @SerializedName("censorship")
    var censorship: String? = null
    @SerializedName("image")
    var image: String? = null
    @SerializedName("image_mini")
    var image_mini: String? = null
    @SerializedName("slide01")
    var slide01: String? = null
    @SerializedName("slide02")
    var slide02: String? = null
    @SerializedName("slide03")
    var slide03: String? = null
    @SerializedName("trailer")
    var trailer: String? = null
    @SerializedName("sessions")
    var grid: ArrayList<Grid>? = null

}