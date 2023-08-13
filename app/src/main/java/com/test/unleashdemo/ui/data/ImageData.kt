package com.test.unleashdemo.ui.data

data class ImageData(
    val url: String?,
    val id: String,
    val description: String?,
    val contentDescription: String?,
    val likes: String?
)

fun emptyImage() =
    ImageData(url = null, id = "", description = null, contentDescription = null, likes = null)