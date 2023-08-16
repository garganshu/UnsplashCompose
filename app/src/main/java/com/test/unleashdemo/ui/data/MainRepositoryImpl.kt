package com.test.unleashdemo.ui.data

import com.test.unleashdemo.ui.domain.ImageDownloader
import com.test.unleashdemo.ui.domain.ImageMapper
import com.test.unleashdemo.ui.domain.MainRepository
import com.test.unleashdemo.utils.UnleashResponse
import com.test.unleashdemo.utils.responseMap

class MainRepositoryImpl(
    private val remoteDataStore: MainRemoteDataStore,
    private val imageDownloader: ImageDownloader,
    private val mapper: ImageMapper
) : MainRepository {
    override suspend fun getData(): UnleashResponse<List<ImageData>> {
        return remoteDataStore.getData().responseMap {
            mapper.toImageDataList(imageDataModelList = it)
        }
    }

    override suspend fun isImageDetailsToggleEnabled(): Boolean {
        return remoteDataStore.isImageDetailsToggleEnabled()
    }

    override suspend fun downloadImage(imageData: ImageData) {
        imageDownloader.downloadImage(imageData)
    }
}