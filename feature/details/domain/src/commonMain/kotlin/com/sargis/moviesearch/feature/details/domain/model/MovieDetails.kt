package com.sargis.moviesearch.feature.details.domain.model

data class MovieDetails(
    val id: String,
    val title: String,
    val overview: String,
    val imageUrl: String,
    val originCountry: List<String>,
    val originalLanguage: String,
    val posterPath: String?,
    val releaseDate: String?,
    val averageVote: Double?,
    val voteCount: Int?
)