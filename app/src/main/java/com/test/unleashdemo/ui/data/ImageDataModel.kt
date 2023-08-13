package com.test.unleashdemo.ui.data

import com.google.gson.annotations.SerializedName

data class ImageDataModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("alt_description")
    val alt_description: String?,
    @SerializedName("likes")
    val likes: Int?,
    @SerializedName("urls")
    val urls: ImageUrlDataModel?
)

data class ImageUrlDataModel(
    @SerializedName("regular")
    val regular: String?
)