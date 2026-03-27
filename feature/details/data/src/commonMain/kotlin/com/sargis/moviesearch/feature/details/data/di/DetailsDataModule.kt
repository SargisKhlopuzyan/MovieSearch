package com.sargis.moviesearch.feature.details.data.di

import com.sargis.moviesearch.feature.details.data.repository.DetailsRepositoryImpl
import com.sargis.moviesearch.feature.details.domain.repository.DetailsRepository
import org.koin.core.module.Module
import org.koin.dsl.module

fun getDetailsDataModule(): Module {
    return module {
        single<DetailsRepository> {
            DetailsRepositoryImpl(get())
        }
    }
}