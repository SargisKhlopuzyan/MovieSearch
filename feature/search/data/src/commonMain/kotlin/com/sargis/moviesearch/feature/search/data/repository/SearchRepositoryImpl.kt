package com.sargis.moviesearch.feature.search.data.repository

import com.sargis.moviesearch.core.data.api.KtorRemoteMovieDataSource
import com.sargis.moviesearch.core.domain.DataError
import com.sargis.moviesearch.core.domain.Result
import com.sargis.moviesearch.core.domain.map
import com.sargis.moviesearch.feature.search.data.mapper.toMovie
import com.sargis.moviesearch.feature.search.domain.model.Movie
import com.sargis.moviesearch.feature.search.domain.repository.SearchRepository

class SearchRepositoryImpl(
    private val remoteMovieDataSource: KtorRemoteMovieDataSource
) : SearchRepository {
    override suspend fun search(q: String): Result<List<Movie>, DataError> {
        return remoteMovieDataSource.search(q).map { movies ->
            movies.results.map { movieDto ->
                movieDto.toMovie()
            }
        }
    }
}

// https://image.tmdb.org/t/p/original/{path}
fun buildImageUrl(path: String?): String {
    return if (path.isNullOrEmpty()) "" else "https://image.tmdb.org/t/p/original/$path"
}