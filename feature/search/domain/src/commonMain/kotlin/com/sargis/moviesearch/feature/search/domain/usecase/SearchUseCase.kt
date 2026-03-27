package com.sargis.moviesearch.feature.search.domain.usecase

import com.sargis.moviesearch.feature.search.domain.model.Movie
import com.sargis.moviesearch.feature.search.domain.repository.SearchRepository

class SearchUseCase(
    private val searchRepository: SearchRepository
) {
    suspend fun execute(q: String): Result<List<Movie>> {
        return searchRepository.search(q)
    }
}