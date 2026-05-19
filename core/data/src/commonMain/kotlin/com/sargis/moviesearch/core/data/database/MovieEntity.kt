package com.sargis.moviesearch.core.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity/*(tableName = "movie")*/
data class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val title: String,
    val overview: String,
    val imageUrl: String,
    val originCountry: List<String>,
    val originalLanguage: String,
    val posterPath: String?,
    val releaseDate: String?,
    val averageVote: Double?,
    val voteCount: Int?
)