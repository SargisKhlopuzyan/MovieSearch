package com.sargis.moviesearch.feature.details.ui.di

import com.sargis.moviesearch.feature.details.ui.DetailsViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

actual fun getDetailsUiModule(): Module {
    return module {
        viewModelOf(::DetailsViewModel)
//        viewModel {
//            DetailsViewModel(get())
//        }
    }
}