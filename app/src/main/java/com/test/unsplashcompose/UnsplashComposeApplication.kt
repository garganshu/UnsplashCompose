package com.test.unsplashcompose

import android.app.Application
import com.test.unsplashcompose.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class UnsplashComposeApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@UnsplashComposeApplication)
            modules(appModule)
        }
    }

}