package com.soucriador.cynema.model.response

import com.soucriador.cynema.model.Snack
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

class SnackResponse {
    @SerializedName("snacks")
    var results: ArrayList<Snack>? = null
}