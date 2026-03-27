package com.sargis.moviesearch.shared.di

import com.sargis.moviesearch.core.network.di.getCoreNetworkModule
import com.sargis.moviesearch.feature.details.data.di.getDetailsDataModule
import com.sargis.moviesearch.feature.search.data.di.getSearchDataModule
import com.sargis.moviesearch.feature.details.domain.di.getDetailsDomainModule
import com.sargis.moviesearch.feature.search.domain.di.getSearchDomainModule
import com.sargis.moviesearch.feature.search.ui.di.getSearchUiModule
import com.sargis.moviesearch.feature.details.ui.di.getDetailsUiModule
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