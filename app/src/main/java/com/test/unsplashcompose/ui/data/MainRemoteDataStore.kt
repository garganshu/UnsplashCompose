package com.test.unsplashcompose.ui.data

import com.test.unsplashcompose.utils.Response

interface MainRemoteDataStore {
    suspend fun getData(): Response<List<ImageDataModel>>
}