package com.test.unsplashcompose.ui.data

interface FeatureToggleRemoteDataStore {
    suspend fun isImageDetailsToggleEnabled(): Boolean
}