package com.sargis.moviesearch.feature.details.domain.repository

import com.sargis.moviesearch.feature.details.domain.model.MovieDetails

interface DetailsRepository {
    suspend fun getMovieDetails(movieId: String): Result<MovieDetails>
}