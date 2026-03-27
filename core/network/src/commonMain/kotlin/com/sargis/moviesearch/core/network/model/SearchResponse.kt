package com.sargis.moviesearch.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    val page: Int,
    val results: List<MovieDTO>,
)