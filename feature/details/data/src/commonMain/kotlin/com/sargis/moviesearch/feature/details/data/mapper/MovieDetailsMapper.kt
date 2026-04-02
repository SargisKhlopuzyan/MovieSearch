package com.sargis.moviesearch.feature.details.data.mapper

import com.sargis.moviesearch.core.data.dto.MovieDetailsResponseDto
import com.sargis.moviesearch.feature.details.data.repository.buildImageUrl
import com.sargis.moviesearch.feature.details.domain.model.MovieDetails

fun MovieDetailsResponseDto.toMovieDetails() = MovieDetails(
    id = id.toString(),
    title = title,
    overview = overview,
    imageUrl = buildImageUrl(posterPath),
    originCountry = originCountry,
    originalLanguage = originalLanguage,
    posterPath = posterPath,
    releaseDate = releaseDate,
    averageVote = averageVote,
    voteCount = voteCount
)