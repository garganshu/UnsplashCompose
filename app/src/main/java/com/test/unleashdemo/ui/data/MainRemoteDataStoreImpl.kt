package com.test.unleashdemo.ui.data

import android.util.Log
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
        val isToggleEnabled = unleashClient.isEnabled("ImageDetailsToggle", false)
        Log.d("Check: ", "State: $isToggleEnabled")
        return isToggleEnabled
    }


}