package com.sargis.moviesearch.shared.di

import com.sargis.moviesearch.core.data.di.getCoreDataModule
import com.sargis.moviesearch.core.data.di.platformCoreDataModule
import com.sargis.moviesearch.feature.details.data.di.getDetailsDataModule
import com.sargis.moviesearch.feature.details.domain.di.getDetailsDomainModule
import com.sargis.moviesearch.feature.details.ui.di.getDetailsUiModule
import com.sargis.moviesearch.feature.search.data.di.getSearchDataModule
import com.sargis.moviesearch.feature.search.domain.di.getSearchDomainModule
import com.sargis.moviesearch.feature.search.ui.di.getSearchUiModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            platformCoreDataModule,
            getCoreDataModule(),

            getSearchDataModule(),
            getSearchDomainModule(),
            getSearchUiModule(),

            getDetailsDataModule(),
            getDetailsDomainModule(),
            getDetailsUiModule(),
        )
    }
}