package br.com.soucriador.cynema.cynema.model

import com.google.gson.annotations.SerializedName

class Drink {

    @SerializedName("id")
    var id: String? = null
    @SerializedName("code")
    var code: Int = 0
    @SerializedName("preview")
    var preview: String? = null
    @SerializedName("category")
    var category: String? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("ingredients")
    var ingredients: String? = null

    @SerializedName("price")
    var price: Double? = null

    @SerializedName("status")
    var status: Int = 0

    @SerializedName("active")
    var active: Boolean = false

    @SerializedName("available")
    var available: Boolean = false

}