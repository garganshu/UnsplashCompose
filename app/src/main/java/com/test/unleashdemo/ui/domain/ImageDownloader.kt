package com.test.unleashdemo.ui.domain

import com.test.unleashdemo.ui.data.ImageData

interface ImageDownloader {
    suspend fun downloadImage(imageData: ImageData)
}