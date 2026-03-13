package com.sargis.ui

import com.sargis.domain.model.Movie

data class SearchUiState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: List<Movie>? = null
)