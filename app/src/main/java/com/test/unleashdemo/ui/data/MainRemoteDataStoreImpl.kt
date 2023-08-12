package com.test.unleashdemo.ui.data

import com.test.unleashdemo.utils.UnleashResponse
import com.test.unleashdemo.utils.getUnleashResponse

class MainRemoteDataStoreImpl(
    private val apiService: ApiService
) : MainRemoteDataStore {
    override suspend fun getData(): UnleashResponse<List<ImageDataModel>> {
        return getUnleashResponse {
            apiService.getData()
        }
    }
}