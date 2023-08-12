package com.test.unleashdemo.ui.data

import com.google.gson.annotations.SerializedName

data class ImageDataModel(
    @SerializedName("urls")
    val urls: ImageUrlDataModel?
)

data class ImageUrlDataModel(
    @SerializedName("regular")
    val regular: String?
)