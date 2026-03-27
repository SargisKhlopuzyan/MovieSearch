package com.sargis.moviesearch.ui.di

import com.sargis.moviesearch.ui.DetailsViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

actual fun getDetailsUiModule(): Module {
    return module {
        viewModel {
            DetailsViewModel(get())
        }
    }
}