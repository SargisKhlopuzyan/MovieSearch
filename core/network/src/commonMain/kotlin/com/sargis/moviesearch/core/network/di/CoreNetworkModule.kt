package com.sargis.moviesearch.core.network.di

import com.sargis.moviesearch.core.network.client.HttpClientFactory
import com.sargis.moviesearch.core.network.service.MovieApiService
import org.koin.core.module.Module
import org.koin.dsl.module

fun getCoreNetworkModule(): Module {
    return module {
        single {
            MovieApiService(HttpClientFactory.getInstance())
        }
    }
}