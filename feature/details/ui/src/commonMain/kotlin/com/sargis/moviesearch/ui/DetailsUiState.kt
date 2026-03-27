package com.sargis.moviesearch.ui

import com.sargis.moviesearch.domain.model.MovieDetails

data class DetailsUiState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: MovieDetails? = null
)