package com.sargis.moviesearch.ui

import androidx.compose.runtime.Immutable
import com.sargis.moviesearch.coreui.UiText
import com.sargis.moviesearch.domain.model.Movie

@Immutable
data class SearchUiState(
    val isLoading: Boolean = false,
    val error: UiText? = null,
    val searchedMovies: List<Movie> = emptyList(),
    val favoriteMovies: List<Movie> = emptyList(),
    val selectedTabIndex: Int = 0
)