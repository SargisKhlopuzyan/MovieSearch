package com.sargis.moviesearch.feature.search.ui.di

import com.sargis.moviesearch.feature.search.ui.SearchViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

actual fun getSearchUiModule(): Module {
    return module {
        viewModel {
            SearchViewModel(get())
        }
    }
}