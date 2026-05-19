package com.sargis.moviesearch.feature.search.data.repository

import com.sargis.moviesearch.core.data.api.KtorRemoteMovieDataSource
import com.sargis.moviesearch.core.data.database.FavoriteMovieDao
import com.sargis.moviesearch.core.domain.DataError
import com.sargis.moviesearch.core.domain.Result
import com.sargis.moviesearch.core.domain.map
import com.sargis.moviesearch.feature.search.data.mapper.toMovie
import com.sargis.moviesearch.feature.search.domain.model.Movie
import com.sargis.moviesearch.feature.search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SearchRepositoryImpl(
    private val remoteMovieDataSource: KtorRemoteMovieDataSource,
    private val favoriteMovieDao: FavoriteMovieDao
) : SearchRepository {

    override suspend fun search(q: String): Result<List<Movie>, DataError> {
        return remoteMovieDataSource.searchMovie(q).map { movies ->
            movies.results.map { movieDto ->
                movieDto.toMovie()
            }
        }
    }

    override fun observeFavoriteMovies(): Flow<List<Movie>> {
        return favoriteMovieDao.observeFavoriteMovies().map { movieEntities ->
            movieEntities.map { movieEntity ->
                movieEntity.toMovie()
            }
        }
    }
}

// https://image.tmdb.org/t/p/original/{path}
fun buildImageUrl(path: String?): String {
    return if (path.isNullOrEmpty()) "" else "https://image.tmdb.org/t/p/original/$path"
}