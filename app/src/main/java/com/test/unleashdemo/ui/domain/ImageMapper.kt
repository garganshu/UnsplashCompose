package com.test.unleashdemo.ui.domain

import com.test.unleashdemo.ui.data.ImageData
import com.test.unleashdemo.ui.data.ImageDataModel

interface ImageMapper {
    fun toImageDataList(imageDataModelList: List<ImageDataModel>): List<ImageData>
}