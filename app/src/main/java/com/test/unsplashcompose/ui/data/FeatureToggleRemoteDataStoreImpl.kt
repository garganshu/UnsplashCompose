package com.test.unsplashcompose.ui.data

import io.getunleash.UnleashClient

class FeatureToggleRemoteDataStoreImpl(
    private val unleashClient: UnleashClient
) : FeatureToggleRemoteDataStore {

    override suspend fun isImageDetailsToggleEnabled(): Boolean {
        return unleashClient.isEnabled("ImageDetailsEnabled", true)
    }
}