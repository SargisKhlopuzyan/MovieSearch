package com.sargis.moviesearch.feature.details.domain.repository

import com.sargis.moviesearch.core.domain.DataError
import com.sargis.moviesearch.core.domain.EmptyResult
import com.sargis.moviesearch.core.domain.Result
import com.sargis.moviesearch.feature.details.domain.model.MovieDetails
import kotlinx.coroutines.flow.Flow

interface DetailsRepository {
    suspend fun getMovieDetails(movieId: String): Result<MovieDetails, DataError>
    fun observeFavoriteMovies(): Flow<List<MovieDetails>>
    fun isMovieFavorite(id: String): Flow<Boolean>
    suspend fun markAsFavoriteMovie(movieDetails: MovieDetails): EmptyResult<DataError.Local> //Result<Unit, DataError.Local>
    suspend fun deleteFromFavorites(id: String)
}