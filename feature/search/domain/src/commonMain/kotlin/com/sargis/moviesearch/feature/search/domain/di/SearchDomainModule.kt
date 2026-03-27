package com.sargis.moviesearch.feature.search.domain.di

import com.sargis.moviesearch.feature.search.domain.usecase.SearchUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

fun getSearchDomainModule(): Module {
    return module {
        // single
        factory {
            SearchUseCase(get())
        }
    }
}