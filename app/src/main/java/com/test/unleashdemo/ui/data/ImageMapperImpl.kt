package com.test.unleashdemo.ui.data

import com.test.unleashdemo.ui.domain.ImageMapper

class ImageMapperImpl : ImageMapper {
    override fun toImageDataList(imageDataModelList: List<ImageDataModel>): List<ImageData> {
        return imageDataModelList.map {
            ImageData(
                url = it.urls?.regular,
                id = it.id,
                description = it.description,
                contentDescription = it.alt_description,
                likes = it.likes.toString()
            )
        }
    }
}