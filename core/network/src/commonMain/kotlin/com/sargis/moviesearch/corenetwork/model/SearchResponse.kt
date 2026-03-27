package com.sargis.moviesearch.corenetwork.model

import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    val page: Int,
    val results: List<MovieDTO>,
)