package com.test.unsplashcompose.ui.domain

interface FeatureToggleRepository {
    suspend fun isImageDetailsToggleEnabled(): Boolean
}