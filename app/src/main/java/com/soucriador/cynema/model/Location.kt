package br.com.soucriador.cynema.cynema.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.ArrayList

class Location : Serializable {

    @SerializedName("id")
    var id: Int = 0
    @SerializedName("name")
    var name: String? = null
    @SerializedName("sessions")
    var sessions: ArrayList<Session>? = null

}