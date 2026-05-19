package com.sargis.moviesearch.core.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMovieDao {

    @Upsert
    suspend fun upsert(movie: MovieEntity)

    @Query("SELECT * FROM MovieEntity")
    fun observeFavoriteMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM MovieEntity WHERE id = :id")
    fun getFavoriteMovie(id: String): MovieEntity?

    @Query("DELETE FROM MovieEntity WHERE id = :id")
    fun deleteFavoriteMovie(id: String): Int
}