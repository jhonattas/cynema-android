package br.com.patrocine.patrocine.model

import com.google.gson.annotations.SerializedName

class Grid {

    @SerializedName("id")
    var id: Int = 0
    @SerializedName("movie_id")
    var MovieId: Int = 0
    @SerializedName("date")
    var date: String? = null
    @SerializedName("room")
    var room: String? = null
    @SerializedName("category")
    var category: String? = null
    @SerializedName("language")
    var language: String? = null
    @SerializedName("day")
    var day: String? = null
    @SerializedName("hour")
    var hour: String? = null

}