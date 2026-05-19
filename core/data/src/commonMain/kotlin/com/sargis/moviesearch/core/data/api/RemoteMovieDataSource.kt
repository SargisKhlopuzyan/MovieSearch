package com.sargis.moviesearch.core.data.api

import com.sargis.moviesearch.core.data.dto.MovieDetailsResponseDto
import com.sargis.moviesearch.core.data.dto.SearchResponseDto
import com.sargis.moviesearch.core.domain.DataError
import com.sargis.moviesearch.core.domain.Result

interface RemoteMovieDataSource {
    suspend fun searchMovie(q: String): Result<SearchResponseDto, DataError.Remote>
    suspend fun getMovieDetail(movieId: Int): Result<MovieDetailsResponseDto, DataError>
}