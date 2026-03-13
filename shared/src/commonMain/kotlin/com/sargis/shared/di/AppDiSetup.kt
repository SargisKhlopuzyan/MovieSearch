package com.sargis.shared.di

import com.sargis.corenetwork.di.getCoreNetworkModule
import com.sargis.data.di.getSearchDataModule
import com.sargis.domain.di.getSearchDomainModule
import com.sargis.ui.di.getSearchUiModule
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(
            getCoreNetworkModule(),
            getSearchUiModule(),
            getSearchDomainModule(),
            getSearchDataModule(),
            // getDetailsUiModule(),
            // getDetailsDomainModule(),
            // getDetailsDataModule(),
        )
    }
}