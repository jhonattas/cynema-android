package br.com.soucriador.cynema.cynema.model.response

import br.com.patrocine.cynema.model.Snack
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

class SnackResponse {
    @SerializedName("snacks")
    var results: ArrayList<Snack>? = null
}