package com.sargis.moviesearch.domain.repository

import com.sargis.moviesearch.domain.model.Movie

interface SearchRepository {
    suspend fun search(q: String): Result<List<Movie>>
}