package com.sargis.moviesearch.data.di

import com.sargis.moviesearch.data.repository.SearchRepositoryImpl
import com.sargis.moviesearch.domain.repository.SearchRepository
import org.koin.core.module.Module
import org.koin.dsl.module

fun getSearchDataModule(): Module {
    return module {
        single<SearchRepository> {
            SearchRepositoryImpl(get())
        }
    }
}