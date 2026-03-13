package com.sargis.shared

import android.app.Application
import com.sargis.shared.di.initKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}