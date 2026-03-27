package com.sargis.moviesearch.feature.search.domain.model

data class Movie(
    val id: String,
    val title: String,
    val imageUrl: String,
    val releaseDate: String,
    val averageVote: Double,
    val voteCount: Int
)