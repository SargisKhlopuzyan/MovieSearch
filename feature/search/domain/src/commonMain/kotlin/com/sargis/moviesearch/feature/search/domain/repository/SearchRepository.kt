package com.sargis.moviesearch.feature.search.domain.repository

import com.sargis.moviesearch.core.domain.DataError
import com.sargis.moviesearch.core.domain.Result
import com.sargis.moviesearch.feature.search.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun search(q: String): Result<List<Movie>, DataError>
    fun observeFavoriteMovies(): Flow<List<Movie>>
}