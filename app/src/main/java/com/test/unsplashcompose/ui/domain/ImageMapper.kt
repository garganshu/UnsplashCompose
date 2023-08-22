package com.test.unsplashcompose.ui.domain

import com.test.unsplashcompose.ui.data.ImageData
import com.test.unsplashcompose.ui.data.ImageDataModel

interface ImageMapper {
    fun toImageDataList(imageDataModelList: List<ImageDataModel>): List<ImageData>
}