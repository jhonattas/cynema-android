package br.com.patrocine.patrocine.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Layout() : Serializable, Parcelable {

    @SerializedName("id")
    var id: Int = 0
    @SerializedName("background")
    var background: String? = null
    @SerializedName("news")
    var news: String? = null
    @SerializedName("news_link")
    var newsLink: String? = null
    @SerializedName("featured")
    var featured: String? = null
    @SerializedName("status")
    var status: Int = 0

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        background = parcel.readString()
        news = parcel.readString()
        newsLink = parcel.readString()
        featured = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(background)
        parcel.writeString(news)
        parcel.writeString(newsLink)
        parcel.writeString(featured)
        parcel.writeInt(status)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Layout> {
        override fun createFromParcel(parcel: Parcel): Layout {
            return Layout(parcel)
        }

        override fun newArray(size: Int): Array<Layout?> {
            return arrayOfNulls(size)
        }
    }

}

