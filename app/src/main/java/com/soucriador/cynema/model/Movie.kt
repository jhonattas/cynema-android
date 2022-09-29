package com.soucriador.cynema.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.ArrayList

class Movie() : Serializable, Parcelable {

    @SerializedName("id")
    var id: Int = 0
    @SerializedName("title")
    var title: String? = null
    @SerializedName("full_title")
    var fullTitle: String? = null
    @SerializedName("sinopse")
    var sinopse: String? = null
    @SerializedName("duration")
    var duration: Int = 0
    @SerializedName("genre")
    var genre: String? = null
    @SerializedName("censorship")
    var censorship: String? = null
    @SerializedName("image")
    var image: String? = null
    @SerializedName("image_mini")
    var image_mini: String? = null
    @SerializedName("slide01")
    var slide01: String? = null
    @SerializedName("slide02")
    var slide02: String? = null
    @SerializedName("slide03")
    var slide03: String? = null
    @SerializedName("trailer")
    var trailer: String? = null
    @SerializedName("sessions")
    var grid: ArrayList<Grid>? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        title = parcel.readString()
        fullTitle = parcel.readString()
        sinopse = parcel.readString()
        duration = parcel.readInt()
        genre = parcel.readString()
        censorship = parcel.readString()
        image = parcel.readString()
        image_mini = parcel.readString()
        slide01 = parcel.readString()
        slide02 = parcel.readString()
        slide03 = parcel.readString()
        trailer = parcel.readString()
    }

    override fun toString(): String {
        return "Movie(id=$id, title=$title, fullTitle=$fullTitle, sinopse=$sinopse, duration=$duration, genre=$genre, censorship=$censorship, image=$image, image_mini=$image_mini, slide01=$slide01, slide02=$slide02, slide03=$slide03, trailer=$trailer, grid=$grid)"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(fullTitle)
        parcel.writeString(sinopse)
        parcel.writeInt(duration)
        parcel.writeString(genre)
        parcel.writeString(censorship)
        parcel.writeString(image)
        parcel.writeString(image_mini)
        parcel.writeString(slide01)
        parcel.writeString(slide02)
        parcel.writeString(slide03)
        parcel.writeString(trailer)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }


}
