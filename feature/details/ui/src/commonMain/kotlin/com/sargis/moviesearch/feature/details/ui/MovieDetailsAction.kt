package com.sargis.moviesearch.feature.details.ui

sealed interface MovieDetailsAction {
    data object OnBackClick : MovieDetailsAction
    data object OnFavoriteClick : MovieDetailsAction
    data class OnSelectedMovieChanged(val movieId: String) : MovieDetailsAction
}