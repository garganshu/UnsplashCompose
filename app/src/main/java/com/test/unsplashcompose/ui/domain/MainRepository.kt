package com.test.unsplashcompose.ui.domain

import com.test.unsplashcompose.ui.data.ImageData
import com.test.unsplashcompose.utils.Response

interface MainRepository {
    suspend fun getData(): Response<List<ImageData>>
    suspend fun downloadImage(imageData: ImageData)
}