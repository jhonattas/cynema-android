package br.com.patrocine.patrocine.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Grid() : Parcelable {

    @SerializedName("id")
    var id: Int = 0
    @SerializedName("movie_id")
    var MovieId: Int = 0
    @SerializedName("date")
    var date: String? = null
    @SerializedName("room")
    var room: String? = null
    @SerializedName("category")
    var category: String? = null
    @SerializedName("language")
    var language: String? = null
    @SerializedName("day")
    var day: String? = null
    @SerializedName("hour")
    var hour: String? = null



    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        MovieId = parcel.readInt()
        date = parcel.readString()
        room = parcel.readString()
        category = parcel.readString()
        language = parcel.readString()
        day = parcel.readString()
        hour = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(MovieId)
        parcel.writeString(date)
        parcel.writeString(room)
        parcel.writeString(category)
        parcel.writeString(language)
        parcel.writeString(day)
        parcel.writeString(hour)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "Grid(id=$id, MovieId=$MovieId, date=$date, room=$room, category=$category, language=$language, day=$day, hour=$hour)"
    }

    companion object CREATOR : Parcelable.Creator<Grid> {
        override fun createFromParcel(parcel: Parcel): Grid {
            return Grid(parcel)
        }

        override fun newArray(size: Int): Array<Grid?> {
            return arrayOfNulls(size)
        }
    }

}