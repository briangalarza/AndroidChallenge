package com.example.androidchallenge.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo(@SerializedName("albumId") val albumId: String,
                 @SerializedName("id") val id: String,
                 @SerializedName("title") val title: String,
                 @SerializedName("url") val imgUrl: String,
                 @SerializedName("thumbnailUrl") val thumbnail: String
                 ) : Parcelable