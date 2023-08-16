package com.test.unleashdemo.ui.data

import com.test.unleashdemo.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("photos")
    suspend fun getData(
        @Query("client_id") key: String = BuildConfig.UNSPLASH_KEY
    ): List<ImageDataModel>
}