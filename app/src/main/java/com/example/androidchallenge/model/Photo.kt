package com.example.androidchallenge.model

import com.google.gson.annotations.SerializedName

data class Photo(@SerializedName("albumId") val albumId: String,
                 @SerializedName("id") val id: String,
                 @SerializedName("title") val title: String,
                 @SerializedName("url") val imgUrl: String,
                 @SerializedName("thumbnailUrl") val thumbnail: String
                 )