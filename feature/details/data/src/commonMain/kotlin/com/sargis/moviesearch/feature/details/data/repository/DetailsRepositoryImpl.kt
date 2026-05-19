package com.sargis.moviesearch.feature.details.data.repository

import androidx.sqlite.SQLiteException
import com.sargis.moviesearch.core.data.api.KtorRemoteMovieDataSource
import com.sargis.moviesearch.core.data.database.FavoriteMovieDao
import com.sargis.moviesearch.core.domain.DataError
import com.sargis.moviesearch.core.domain.EmptyResult
import com.sargis.moviesearch.core.domain.Result
import com.sargis.moviesearch.core.domain.map
import com.sargis.moviesearch.feature.details.data.mapper.toMovieDetails
import com.sargis.moviesearch.feature.details.data.mapper.toMovieEntity
import com.sargis.moviesearch.feature.details.domain.model.MovieDetails
import com.sargis.moviesearch.feature.details.domain.repository.DetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DetailsRepositoryImpl(
    private val remoteMovieDataSource: KtorRemoteMovieDataSource, //apiService
    private val favoriteMovieDao: FavoriteMovieDao
) : DetailsRepository {
    override suspend fun getMovieDetails(movieId: String): Result<MovieDetails, DataError> {
        val localResult = favoriteMovieDao.getFavoriteMovie(movieId)
        return if (localResult != null) {
            Result.Success(localResult.toMovieDetails())
        } else {
            remoteMovieDataSource.getMovieDetail(movieId.toInt()).map { movieDetailResponseDto ->
                movieDetailResponseDto.toMovieDetails()
            }
        }
    }

    override fun observeFavoriteMovies(): Flow<List<MovieDetails>> {
        return favoriteMovieDao.observeFavoriteMovies().map { movieEntities ->
            movieEntities.map { movieEntity ->
                movieEntity.toMovieDetails()
            }
        }
    }

    override fun isMovieFavorite(id: String): Flow<Boolean> {
        return favoriteMovieDao.observeFavoriteMovies().map { movieEntities ->
            movieEntities.any { it.id == id }
        }
    }

    override suspend fun markAsFavoriteMovie(movieDetails: MovieDetails): EmptyResult<DataError.Local> {
        return try {
            favoriteMovieDao.upsert(movieDetails.toMovieEntity())
            Result.Success(Unit)
        } catch (e: SQLiteException) {
            return Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun deleteFromFavorites(id: String) {
        favoriteMovieDao.deleteFavoriteMovie(id)
    }
}

// https://image.tmdb.org/t/p/original/{path}
fun buildImageUrl(path: String?): String {
    return if (path.isNullOrEmpty()) "" else "https://image.tmdb.org/t/p/original/$path"
}