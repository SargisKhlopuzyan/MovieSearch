package com.sargis.moviesearch.data.repository

import com.sargis.moviesearch.corenetwork.service.MovieApiService
import com.sargis.moviesearch.domain.model.MovieDetails
import com.sargis.moviesearch.domain.repository.DetailsRepository

class DetailsRepositoryImpl(
    private val apiService: MovieApiService
) : DetailsRepository {
    override suspend fun getMovieDetails(movieId: String): Result<MovieDetails> {
        return apiService.getMovieDetail(movieId.toInt()).map { movieDetailResponse ->
            MovieDetails(
                id = movieDetailResponse.id.toString(),
                title = movieDetailResponse.title,
                overview = movieDetailResponse.overview,
                imageUrl = buildImageUrl(movieDetailResponse.poster_path)
            )
        }
    }

    // https://image.tmdb.org/t/p/original/{path}
    private fun buildImageUrl(path: String?): String {
        return if (path.isNullOrEmpty()) "" else "https://image.tmdb.org/t/p/original/$path"
    }
}