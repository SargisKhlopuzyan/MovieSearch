package com.sargis.moviesearch.ui.di

import com.sargis.moviesearch.ui.DetailsViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.module.Module
import org.koin.dsl.module

private val detailsViewModelModule = module {
    single {
        DetailsViewModel(get())
    }
}

actual fun getDetailsUiModule(): Module {
    return detailsViewModelModule
}

class DetailsViewModelProvider() : KoinComponent {
    fun provideDetailsViewModel(): DetailsViewModel = get()
}