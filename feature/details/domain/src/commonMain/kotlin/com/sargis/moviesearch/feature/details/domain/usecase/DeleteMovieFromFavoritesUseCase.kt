package com.sargis.moviesearch.feature.details.domain.usecase

import com.sargis.moviesearch.feature.details.domain.repository.DetailsRepository

class DeleteMovieFromFavoritesUseCase(
    private val detailsRepository: DetailsRepository
) {
    suspend fun execute(movieId: String) {
        detailsRepository.deleteFromFavorites(movieId)
    }
}