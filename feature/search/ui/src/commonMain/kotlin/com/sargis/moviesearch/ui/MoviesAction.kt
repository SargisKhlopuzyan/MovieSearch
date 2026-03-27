package com.sargis.moviesearch.ui

import com.sargis.moviesearch.domain.model.Movie

sealed interface MoviesAction {
    data class OnSearchQueryChange(val query: String) : MoviesAction
    data class OnMovieClick(val movie: Movie) : MoviesAction
    data class OnTabSelected(val tabIndex: Int) : MoviesAction
}