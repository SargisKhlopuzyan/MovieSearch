package com.sargis.moviesearch.feature.details.domain.di

import com.sargis.moviesearch.feature.details.domain.usecase.GetMovieDetailsUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

fun getDetailsDomainModule(): Module {
    return module {
        // single
        factory {
            GetMovieDetailsUseCase(get())
        }
    }
}