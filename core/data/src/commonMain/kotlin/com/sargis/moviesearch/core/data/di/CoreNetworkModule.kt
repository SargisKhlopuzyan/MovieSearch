package com.sargis.moviesearch.core.data.di

import com.sargis.moviesearch.core.data.api.KtorRemoteMovieDataSource
import com.sargis.moviesearch.core.data.api.RemoteMovieDataSource
import com.sargis.moviesearch.core.data.client.HttpClientFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

//fun getCoreDataModule(): Module {
//    return module {
//        single {
//            KtorRemoteMovieDataSource(HttpClientFactory.getInstance())
//        }
//    }
//}

expect val platformCoreDataModule: Module

fun getCoreDataModule() = module {
    single {
        HttpClientFactory.create(get())
    }
//    singleOf(::KtorRemoteMovieDataSource)
    singleOf(::KtorRemoteMovieDataSource).bind<RemoteMovieDataSource>()

//    single {
//        get<DatabaseFactory>().create()
//            .setDriver(BundledSQLiteDriver())
//            .build()
//    }
//    single { get<FavoriteBookDatabase>().favoriteBookDao }
}