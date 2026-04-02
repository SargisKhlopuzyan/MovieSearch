package com.sargis.moviesearch.core.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class SearchResponseDto(
    val page: Int,
    val results: List<MovieDto>,
)