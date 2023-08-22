package com.test.unsplashcompose.ui.data

import com.test.unsplashcompose.ui.domain.ImageDownloader
import com.test.unsplashcompose.ui.domain.ImageMapper
import com.test.unsplashcompose.ui.domain.MainRepository
import com.test.unsplashcompose.utils.Response
import com.test.unsplashcompose.utils.responseMap

class MainRepositoryImpl(
    private val remoteDataStore: MainRemoteDataStore,
    private val imageDownloader: ImageDownloader,
    private val mapper: ImageMapper
) : MainRepository {
    override suspend fun getData(): Response<List<ImageData>> {
        return remoteDataStore.getData().responseMap {
            mapper.toImageDataList(imageDataModelList = it)
        }
    }

    override suspend fun downloadImage(imageData: ImageData) {
        imageDownloader.downloadImage(imageData)
    }
}