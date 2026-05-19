package com.sargis.moviesearch.core.data.dto.notUsedButImportant

import kotlinx.serialization.Serializable

@Serializable(with = MovieTODODtoSerializer::class)
data class MovieTODODto(
//    val id: Int,
    val description: String? = null
)
