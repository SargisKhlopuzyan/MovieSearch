package com.sargis.domain.usecase

import com.sargis.domain.model.Movie
import com.sargis.domain.repository.SearchRepository

class SearchUseCase(
    private val searchRepository: SearchRepository
) {
    suspend fun execute(q: String): Result<List<Movie>> {
        return searchRepository.search(q)
    }
}