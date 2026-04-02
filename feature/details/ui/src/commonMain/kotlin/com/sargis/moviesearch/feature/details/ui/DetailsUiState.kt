package com.sargis.moviesearch.feature.details.ui

import com.sargis.moviesearch.feature.details.domain.model.MovieDetails

data class DetailsUiState(
    val isLoading: Boolean = false,
    val isFavorite: Boolean = false,
    val error: String = "",
    val movieDetails: MovieDetails? = null
)