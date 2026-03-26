package com.sargis.corenetwork.di

import com.sargis.corenetwork.client.HttpClientFactory
import com.sargis.corenetwork.service.MovieApiService
import org.koin.core.module.Module
import org.koin.dsl.module

fun getCoreNetworkModule(): Module {
    return module {
        single {
            MovieApiService(HttpClientFactory.getInstance())
        }
    }
}