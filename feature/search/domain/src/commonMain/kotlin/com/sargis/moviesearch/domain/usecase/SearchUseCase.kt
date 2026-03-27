package com.sargis.moviesearch.domain.usecase

import com.sargis.moviesearch.domain.model.Movie
import com.sargis.moviesearch.domain.repository.SearchRepository

class SearchUseCase(
    private val searchRepository: SearchRepository
) {
    suspend fun execute(q: String): Result<List<Movie>> {
        return searchRepository.search(q)
    }
}