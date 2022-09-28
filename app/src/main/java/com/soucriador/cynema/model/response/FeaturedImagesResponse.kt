package br.com.soucriador.cynema.cynema.model.response

import br.com.patrocine.cynema.model.FeaturedImage
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class FeaturedImagesResponse : Serializable{

    @SerializedName("images")
    var images: ArrayList<FeaturedImage>? = null

}