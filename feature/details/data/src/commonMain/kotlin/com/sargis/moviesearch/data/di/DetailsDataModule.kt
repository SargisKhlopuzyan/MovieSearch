package com.sargis.moviesearch.data.di

import com.sargis.moviesearch.data.repository.DetailsRepositoryImpl
import com.sargis.moviesearch.domain.repository.DetailsRepository
import org.koin.core.module.Module
import org.koin.dsl.module

fun getDetailsDataModule(): Module {
    return module {
        single<DetailsRepository> {
            DetailsRepositoryImpl(get())
        }
    }
}