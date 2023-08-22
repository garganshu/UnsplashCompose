package com.test.unsplashcompose.ui.data

import com.test.unsplashcompose.ui.domain.FeatureToggleRepository

class FeatureToggleRepositoryImpl(
    private val remoteDataStore: FeatureToggleRemoteDataStore
) : FeatureToggleRepository {
    override suspend fun isImageDetailsToggleEnabled(): Boolean {
        return remoteDataStore.isImageDetailsToggleEnabled()
    }
}