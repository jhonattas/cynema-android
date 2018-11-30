package br.com.patrocine.patrocine.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Session : Serializable {

    @SerializedName("start")
    var start: String? = null
    @SerializedName("category")
    var category: String? = null
    @SerializedName("language")
    var language: String? = null

    fun Session(start: String, category: String, language: String) {
        this.start = start
        this.category = category
        this.language = language
    }

}