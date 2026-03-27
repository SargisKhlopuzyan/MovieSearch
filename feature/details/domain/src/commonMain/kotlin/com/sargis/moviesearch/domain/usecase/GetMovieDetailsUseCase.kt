package com.sargis.moviesearch.domain.usecase

import com.sargis.moviesearch.domain.model.MovieDetails
import com.sargis.moviesearch.domain.repository.DetailsRepository

class GetMovieDetailsUseCase(
    private val detailsRepository: DetailsRepository
) {
    suspend fun execute(movieId: String): Result<MovieDetails> {
        return detailsRepository.getMovieDetails(movieId)
    }
}