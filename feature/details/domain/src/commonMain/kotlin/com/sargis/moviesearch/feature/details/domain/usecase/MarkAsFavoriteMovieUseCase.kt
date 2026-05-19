package com.sargis.moviesearch.feature.details.domain.usecase

import com.sargis.moviesearch.core.domain.DataError
import com.sargis.moviesearch.core.domain.EmptyResult
import com.sargis.moviesearch.feature.details.domain.model.MovieDetails
import com.sargis.moviesearch.feature.details.domain.repository.DetailsRepository

class MarkAsFavoriteMovieUseCase(
    private val detailsRepository: DetailsRepository
) {
    suspend fun execute(movie: MovieDetails): EmptyResult<DataError.Local> {
        return detailsRepository.markAsFavoriteMovie(movie)
    }
}