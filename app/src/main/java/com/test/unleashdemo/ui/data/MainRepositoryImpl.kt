package com.test.unleashdemo.ui.data

import com.test.unleashdemo.ui.domain.ImageMapper
import com.test.unleashdemo.ui.domain.MainRepository
import com.test.unleashdemo.utils.UnleashResponse
import com.test.unleashdemo.utils.responseMap

class MainRepositoryImpl(
    private val remoteDataStore: MainRemoteDataStore,
    private val mapper: ImageMapper
) : MainRepository {
    override suspend fun getData(): UnleashResponse<List<ImageData>> {
        return remoteDataStore.getData().responseMap {
            mapper.toImageDataList(imageDataModelList = it)
        }
    }
}