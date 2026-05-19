package com.sargis.moviesearch.feature.details.domain.usecase

import com.sargis.moviesearch.feature.details.domain.repository.DetailsRepository
import kotlinx.coroutines.flow.Flow

class IsMoveFavoriteUseCase(
    private val detailsRepository: DetailsRepository
) {
    fun execute(id: String): Flow<Boolean> {
        return detailsRepository.isMovieFavorite(id)
    }
}