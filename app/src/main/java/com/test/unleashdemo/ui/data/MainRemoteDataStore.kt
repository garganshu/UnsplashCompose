package com.test.unleashdemo.ui.data

import com.test.unleashdemo.utils.UnleashResponse

interface MainRemoteDataStore {
    suspend fun getData(): UnleashResponse<List<ImageDataModel>>
    suspend fun isImageDetailsToggleEnabled(): Boolean
}