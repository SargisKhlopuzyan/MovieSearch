package com.sargis.moviesearch.feature.search.domain.mock

import com.sargis.moviesearch.feature.search.domain.model.Movie
import kotlin.random.Random


val mockMovie = Movie(
    "1",
    "Harry Potter",
    "https://image.tmdb.org/t/p/original//qJ2tW6WMUDux911r6m7haRef0WH.jpg",
    "2014-10-23",
    Random.nextDouble(10.000),
    Random.nextInt(10)
)

val mockMovies = listOf(
    mockMovie,
    mockMovie.copy(id = "2"),
    mockMovie.copy(id = "3"),
    mockMovie.copy(id = "4")
)