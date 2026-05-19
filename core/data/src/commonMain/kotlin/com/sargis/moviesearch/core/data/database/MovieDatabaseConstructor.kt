package com.sargis.moviesearch.core.data.database

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object MovieDatabaseConstructor: RoomDatabaseConstructor<FavoriteMovieDatabase> {
    override fun initialize(): FavoriteMovieDatabase
}