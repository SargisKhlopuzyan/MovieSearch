package com.sargis.moviesearch.feature.details.ui

import com.sargis.moviesearch.feature.details.domain.model.MovieDetails

sealed interface MovieDetailsAction {
    data object OnBackClick : MovieDetailsAction
    data object OnFavoriteClick : MovieDetailsAction
    data class OnSelectedMovieChanged(val movieDetails: MovieDetails) : MovieDetailsAction
}