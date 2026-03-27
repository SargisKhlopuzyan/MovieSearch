package com.sargis.moviesearch.feature.details.domain.usecase

import com.sargis.moviesearch.feature.details.domain.model.MovieDetails
import com.sargis.moviesearch.feature.details.domain.repository.DetailsRepository

class GetMovieDetailsUseCase(
    private val detailsRepository: DetailsRepository
) {
    suspend fun execute(movieId: String): Result<MovieDetails> {
        return detailsRepository.getMovieDetails(movieId)
    }
}