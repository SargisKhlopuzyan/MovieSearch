package com.sargis.moviesearch.feature.search.data.mapper

import com.sargis.moviesearch.core.data.dto.MovieDto
import com.sargis.moviesearch.feature.search.data.repository.buildImageUrl
import com.sargis.moviesearch.feature.search.domain.model.Movie

fun MovieDto.toMovie() = Movie(
    id = id.toString(),
    title = title,
    imageUrl = buildImageUrl(posterPath),
    releaseDate = releaseDate,
    averageVote = averageVote,
    voteCount = voteCount
)