package com.soucriador.cynema.model.response

import com.soucriador.cynema.model.FeaturedImage
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class FeaturedImagesResponse : Serializable{

    @SerializedName("images")
    var images: ArrayList<FeaturedImage>? = null

}