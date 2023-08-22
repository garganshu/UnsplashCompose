package com.test.unsplashcompose.ui.data

import com.test.unsplashcompose.ui.domain.FeatureToggleRepository
import com.test.unsplashcompose.ui.domain.MainRepository
import com.test.unsplashcompose.ui.domain.MainUseCase
import com.test.unsplashcompose.utils.Response

class MainUseCaseImpl(
    private val mainRepository: MainRepository,
    private val featureToggleRepository: FeatureToggleRepository
) : MainUseCase {
    override suspend fun getData(): Response<List<ImageData>> {
        return mainRepository.getData()
    }

    override suspend fun isImageDetailsToggleEnabled(): Boolean {
        return featureToggleRepository.isImageDetailsToggleEnabled()
    }

    override suspend fun downloadImage(imageData: ImageData) {
        mainRepository.downloadImage(imageData)
    }

}