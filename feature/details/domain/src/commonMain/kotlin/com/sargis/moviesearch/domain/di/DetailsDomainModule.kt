package com.sargis.moviesearch.domain.di

import com.sargis.moviesearch.domain.usecase.GetMovieDetailsUseCase
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