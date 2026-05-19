package com.sargis.moviesearch.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [MovieEntity::class],
    version = 1,
    //    exportSchema = false
)
@TypeConverters(
    StringListTypeConverter::class
)
abstract class FavoriteMovieDatabase : RoomDatabase() {

    abstract val favoriteMovieDao: FavoriteMovieDao

    companion object {
        const val DB_NAME = "movie.db"
    }
}