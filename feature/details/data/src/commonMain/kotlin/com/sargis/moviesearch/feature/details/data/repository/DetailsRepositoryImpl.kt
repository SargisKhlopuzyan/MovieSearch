package com.sargis.moviesearch.feature.details.data.repository

import com.sargis.moviesearch.core.data.api.KtorRemoteMovieDataSource
import com.sargis.moviesearch.core.domain.DataError
import com.sargis.moviesearch.core.domain.Result
import com.sargis.moviesearch.core.domain.map
import com.sargis.moviesearch.feature.details.data.mapper.toMovieDetails
import com.sargis.moviesearch.feature.details.domain.model.MovieDetails
import com.sargis.moviesearch.feature.details.domain.repository.DetailsRepository

class DetailsRepositoryImpl(
    private val apiService: KtorRemoteMovieDataSource
) : DetailsRepository {
    override suspend fun getMovieDetails(movieId: String): Result<MovieDetails, DataError> {
        return apiService.getMovieDetail(movieId.toInt()).map { movieDetailResponseDto ->
            movieDetailResponseDto.toMovieDetails()
        }
    }
}

// https://image.tmdb.org/t/p/original/{path}
fun buildImageUrl(path: String?): String {
    return if (path.isNullOrEmpty()) "" else "https://image.tmdb.org/t/p/original/$path"
}