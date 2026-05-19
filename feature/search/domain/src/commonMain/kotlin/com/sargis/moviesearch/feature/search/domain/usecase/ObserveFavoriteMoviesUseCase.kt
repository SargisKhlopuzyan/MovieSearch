package com.sargis.moviesearch.feature.search.domain.usecase

import com.sargis.moviesearch.feature.search.domain.model.Movie
import com.sargis.moviesearch.feature.search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

class ObserveFavoriteMoviesUseCase(
    private val searchRepository: SearchRepository
) {
    fun execute(): Flow<List<Movie>> {
        return searchRepository.observeFavoriteMovies()
    }
}