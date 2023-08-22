package com.test.unsplashcompose.ui.data

import com.test.unsplashcompose.utils.Response
import com.test.unsplashcompose.utils.getResponse
import io.getunleash.UnleashClient

class MainRemoteDataStoreImpl(
    private val apiService: ApiService
) : MainRemoteDataStore {
    override suspend fun getData(): Response<List<ImageDataModel>> {
        return getResponse {
            apiService.getData()
        }
    }
}