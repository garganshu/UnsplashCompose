package com.test.unleashdemo.di

import com.test.unleashdemo.ui.data.*
import com.test.unleashdemo.ui.domain.ImageMapper
import com.test.unleashdemo.ui.domain.MainRepository
import com.test.unleashdemo.ui.presentation.MainViewModel
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
    factory<MainRemoteDataStore> { MainRemoteDataStoreImpl(get()) }
    factory<MainRepository> { MainRepositoryImpl(get(), get()) }
    factory<ImageMapper> { ImageMapperImpl() }
    viewModel { MainViewModel(get()) }
}

private fun getRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://api.unsplash.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun getApiService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}