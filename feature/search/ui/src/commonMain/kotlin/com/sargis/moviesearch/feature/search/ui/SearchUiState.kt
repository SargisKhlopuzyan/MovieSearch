package com.sargis.moviesearch.feature.search.ui

import androidx.compose.runtime.Immutable
import com.sargis.moviesearch.core.ui.UiText
import com.sargis.moviesearch.feature.search.domain.model.Movie

@Immutable
data class SearchUiState(
    val isLoading: Boolean = false,
    val error: UiText? = null,
    val searchedMovies: List<Movie> = emptyList(),
    val favoriteMovies: List<Movie> = emptyList(),
    val selectedTabIndex: Int = 0
)