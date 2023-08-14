package com.test.unleashdemo.di

import com.test.unleashdemo.BuildConfig
import com.test.unleashdemo.ui.data.*
import com.test.unleashdemo.ui.domain.ImageMapper
import com.test.unleashdemo.ui.domain.MainRepository
import com.test.unleashdemo.ui.presentation.MainViewModel
import io.getunleash.UnleashClient
import io.getunleash.UnleashConfig
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single {
        getRetrofit()
    }
    single {
        getApiService(get())
    }
    single {
        getUnleashClient()
    }
    factory<MainRemoteDataStore> { MainRemoteDataStoreImpl(get(), get()) }
    factory<MainRepository> { MainRepositoryImpl(get(), get()) }
    factory<ImageMapper> { ImageMapperImpl() }
    viewModel { MainViewModel(get()) }
}

private fun getRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.UNSPLASH_API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun getApiService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}

private fun getUnleashClient(): UnleashClient {
    val config = UnleashConfig.newBuilder()
        .proxyUrl(BuildConfig.UNLEASH_API_URL)
        .clientKey(BuildConfig.UNLEASH_CLIENT_KEY)
        .build()
    return UnleashClient(config)
}