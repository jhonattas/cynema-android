package br.com.patrocine.patrocine.model.response

import br.com.patrocine.patrocine.model.Snack
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

class SnackResponse {
    @SerializedName("snacks")
    var results: ArrayList<Snack>? = null
}