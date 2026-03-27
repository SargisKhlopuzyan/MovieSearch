package com.sargis.moviesearch.shared

import android.app.Application
import com.sargis.moviesearch.shared.di.initKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}