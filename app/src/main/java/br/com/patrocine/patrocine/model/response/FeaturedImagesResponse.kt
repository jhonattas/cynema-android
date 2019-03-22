package br.com.patrocine.patrocine.model.response

import br.com.patrocine.patrocine.model.FeaturedImage
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class FeaturedImagesResponse : Serializable{

    @SerializedName("images")
    var images: ArrayList<FeaturedImage>? = null

}