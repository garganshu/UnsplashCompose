package com.test.unsplashcompose.ui.domain

import com.test.unsplashcompose.ui.data.ImageData

interface ImageDownloader {
    suspend fun downloadImage(imageData: ImageData)
}