package com.sargis.moviesearch.corenetwork.di

import com.sargis.moviesearch.corenetwork.client.HttpClientFactory
import com.sargis.moviesearch.corenetwork.service.MovieApiService
import org.koin.core.module.Module
import org.koin.dsl.module

fun getCoreNetworkModule(): Module {
    return module {
        single {
            MovieApiService(HttpClientFactory.getInstance())
        }
    }
}