package com.sargis.moviesearch.shared

import android.app.Application
import com.sargis.moviesearch.shared.di.initKoin
import org.koin.android.ext.koin.androidContext

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
//        initKoin()
        initKoin {
            androidContext(this@BaseApplication)
        }
    }
}