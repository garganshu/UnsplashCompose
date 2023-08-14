package com.test.unleashdemo.ui.domain

import com.test.unleashdemo.ui.data.ImageData
import com.test.unleashdemo.utils.UnleashResponse

interface MainRepository {
    suspend fun getData(): UnleashResponse<List<ImageData>>
    suspend fun isImageDetailsToggleEnabled(): Boolean
}