package com.sargis.moviesearch.shared.di

import com.sargis.moviesearch.corenetwork.di.getCoreNetworkModule
import com.sargis.moviesearch.data.di.getDetailsDataModule
import com.sargis.moviesearch.data.di.getSearchDataModule
import com.sargis.moviesearch.domain.di.getDetailsDomainModule
import com.sargis.moviesearch.domain.di.getSearchDomainModule
import com.sargis.moviesearch.ui.di.getSearchUiModule
import com.sargis.moviesearch.ui.di.getDetailsUiModule
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(
            getCoreNetworkModule(),

            getSearchDataModule(),
            getSearchDomainModule(),
            getSearchUiModule(),

            getDetailsDataModule(),
            getDetailsDomainModule(),
            getDetailsUiModule(),
        )
    }
}