package com.sargis.domain.di

import com.sargis.domain.usecase.SearchUseCase
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