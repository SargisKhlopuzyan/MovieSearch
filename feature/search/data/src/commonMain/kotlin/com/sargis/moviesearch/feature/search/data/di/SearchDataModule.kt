package com.sargis.moviesearch.feature.search.data.di

import com.sargis.moviesearch.feature.search.data.repository.SearchRepositoryImpl
import com.sargis.moviesearch.feature.search.domain.repository.SearchRepository
import org.koin.core.module.Module
import org.koin.dsl.module

fun getSearchDataModule(): Module {
    return module {
        single<SearchRepository> {
            SearchRepositoryImpl(get())
        }
    }
}