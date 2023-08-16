package com.test.unleashdemo.ui.data

import com.test.unleashdemo.utils.UnleashResponse
import com.test.unleashdemo.utils.getUnleashResponse
import io.getunleash.UnleashClient

class MainRemoteDataStoreImpl(
    private val apiService: ApiService,
    private val unleashClient: UnleashClient
) : MainRemoteDataStore {
    override suspend fun getData(): UnleashResponse<List<ImageDataModel>> {
        return getUnleashResponse {
            apiService.getData()
        }
    }

    override suspend fun isImageDetailsToggleEnabled(): Boolean {
        return unleashClient.isEnabled("ImageDetailsEnabled", false)
    }

}