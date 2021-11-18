package com.example.paint

import android.graphics.Path
import android.os.Parcel
import android.os.Parcelable
import kotlinx.serialization.Serializable

@Serializable
class SerializablePath() : Path(), Parcelable {
    val pathPoints: MutableList<FloatArray> = mutableListOf()

    constructor(parcel: Parcel) : this() {

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SerializablePath> {
        override fun createFromParcel(parcel: Parcel): SerializablePath {
            return SerializablePath(parcel)
        }

        override fun newArray(size: Int): Array<SerializablePath?> {
            return arrayOfNulls(size)
        }
    }
}