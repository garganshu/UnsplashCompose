package com.test.unleashdemo.ui.data

import com.test.unleashdemo.ui.domain.ImageMapper

class ImageMapperImpl : ImageMapper {
    override fun toImageDataList(imageDataModelList: List<ImageDataModel>): List<ImageData> {
        return imageDataModelList.map {
            ImageData(imageUrl = it.urls?.regular)
        }
    }
}