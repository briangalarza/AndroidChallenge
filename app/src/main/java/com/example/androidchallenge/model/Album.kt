package com.example.androidchallenge.model

import com.google.gson.annotations.SerializedName

data class Album(@SerializedName("userId") val userId: String,
                 @SerializedName("id") val id: String,
                 @SerializedName("title") val title: String,
                 @SerializedName("imgUrl") val imgUrl: String)