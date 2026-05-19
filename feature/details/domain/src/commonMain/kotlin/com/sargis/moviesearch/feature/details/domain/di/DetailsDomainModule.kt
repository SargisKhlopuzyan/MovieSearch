package com.sargis.moviesearch.feature.details.domain.di

import com.sargis.moviesearch.feature.details.domain.usecase.DeleteMovieFromFavoritesUseCase
import com.sargis.moviesearch.feature.details.domain.usecase.GetMovieDetailsUseCase
import com.sargis.moviesearch.feature.details.domain.usecase.IsMoveFavoriteUseCase
import com.sargis.moviesearch.feature.details.domain.usecase.MarkAsFavoriteMovieUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

fun getDetailsDomainModule(): Module {
    return module {
        // single
        factory {
            GetMovieDetailsUseCase(get())
        }
        // single
        factory {
            DeleteMovieFromFavoritesUseCase(get())
        }
        // single
        factory {
            MarkAsFavoriteMovieUseCase(get())
        }
        // single
        factory {
            IsMoveFavoriteUseCase(get())
        }
    }
}