package com.sargis.ui

import com.sargis.domain.model.MovieDetails

data class DetailsUiState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: MovieDetails? = null
)