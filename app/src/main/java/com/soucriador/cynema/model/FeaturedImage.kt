package br.com.soucriador.cynema.cynema.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class FeaturedImage : Serializable {

    @SerializedName("id")
    var id: Int? = null
    @SerializedName("image")
    var image: String? = null
    @SerializedName("active")
    var active: Int? = null
    @SerializedName("url")
    var url: String? = null
    @SerializedName("description")
    var description: String? = null

}