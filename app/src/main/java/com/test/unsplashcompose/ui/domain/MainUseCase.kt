package com.test.unsplashcompose.ui.domain

import com.test.unsplashcompose.ui.data.ImageData
import com.test.unsplashcompose.utils.Response

interface MainUseCase {
    suspend fun getData(): Response<List<ImageData>>
    suspend fun isImageDetailsToggleEnabled(): Boolean
    suspend fun downloadImage(imageData: ImageData)
}