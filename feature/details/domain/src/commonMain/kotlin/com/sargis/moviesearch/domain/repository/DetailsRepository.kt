package com.sargis.moviesearch.domain.repository

import com.sargis.moviesearch.domain.model.MovieDetails

interface DetailsRepository {
    suspend fun getMovieDetails(movieId: String): Result<MovieDetails>
}