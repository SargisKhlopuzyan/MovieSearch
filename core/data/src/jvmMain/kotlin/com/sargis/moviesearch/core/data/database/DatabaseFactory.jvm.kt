package com.sargis.moviesearch.core.data.database

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

actual class DatabaseFactory {

    actual fun create(): RoomDatabase.Builder<FavoriteMovieDatabase> {
        val os = System.getProperty("os.name").lowercase()
        val userHome = System.getProperty("user.home")
        val appDataPath = when {
            os.contains("win") -> File(System.getenv("APPDATA"), "MovieSearch")
            os.contains("mac") -> File(userHome, "/Library/Application Support/MovieSearch")
            else -> File(System.getProperty(userHome), "/.local/share/MovieSearch")
        }

        if (!appDataPath.exists()) {
            appDataPath.mkdirs()
        }

        val dbFile = File(appDataPath, FavoriteMovieDatabase.DB_NAME)

        return Room.databaseBuilder(dbFile.absolutePath)
    }
}