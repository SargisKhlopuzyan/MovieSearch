package com.sargis.moviesearch.feature.search.domain.repository

import com.sargis.moviesearch.feature.search.domain.model.Movie

interface SearchRepository {
    suspend fun search(q: String): Result<List<Movie>>
}