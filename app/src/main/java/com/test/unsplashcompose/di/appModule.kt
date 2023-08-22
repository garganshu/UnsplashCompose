package com.test.unsplashcompose.di

import android.app.DownloadManager
import android.content.Context
import com.readystatesoftware.chuck.ChuckInterceptor
import com.test.unsplashcompose.BuildConfig
import com.test.unsplashcompose.R
import com.test.unsplashcompose.ui.data.*
import com.test.unsplashcompose.ui.domain.*
import com.test.unsplashcompose.ui.presentation.MainViewModel
import io.getunleash.UnleashClient
import io.getunleash.UnleashConfig
import io.getunleash.UnleashContext
import io.getunleash.polling.AutoPollingMode
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module {

    single {
        getRetrofit(get())
    }
    single {
        getApiService(get())
    }
    single {
        getUnleashClient(get())
    }
    single {
        getDownloadManager(get())
    }
    factory<MainRemoteDataStore> { MainRemoteDataStoreImpl(get()) }
    factory<FeatureToggleRemoteDataStore> { FeatureToggleRemoteDataStoreImpl(get()) }
    factory<MainRepository> { MainRepositoryImpl(get(), get(), get()) }
    factory<FeatureToggleRepository> { FeatureToggleRepositoryImpl(get()) }
    factory<MainUseCase> { MainUseCaseImpl(get(), get()) }
    factory<ImageMapper> { ImageMapperImpl() }
    factory<ImageDownloader> { ImageDownloaderImpl(get()) }

    viewModel { MainViewModel(get()) }
}

private fun getRetrofit(context: Context): Retrofit {
    val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .addInterceptor(ChuckInterceptor(context))
        .build()

    return Retrofit.Builder()
        .baseUrl(BuildConfig.UNSPLASH_API_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun getApiService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}

private fun getUnleashClient(appContext: Context): UnleashClient {
    val appName = appContext.getString(R.string.app_name)
    val context = UnleashContext.newBuilder()
        .appName(appName)
        .build()

    val config = UnleashConfig.newBuilder()
        .appName(appName)
        .enableMetrics()
        .proxyUrl(BuildConfig.UNLEASH_API_URL)
        .clientKey(BuildConfig.UNLEASH_CLIENT_KEY)
        .pollingMode(AutoPollingMode(1000))
        .metricsInterval(5000)
        .build()

    return UnleashClient.newBuilder().unleashConfig(config).unleashContext(context).build()
}

private fun getDownloadManager(context: Context): DownloadManager {
    return context.getSystemService(DownloadManager::class.java)
}