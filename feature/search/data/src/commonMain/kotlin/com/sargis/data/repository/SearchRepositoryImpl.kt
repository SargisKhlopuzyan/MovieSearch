package com.sargis.data.repository

import com.sargis.corenetwork.service.MovieApiService
import com.sargis.domain.model.Movie
import com.sargis.domain.repository.SearchRepository

class SearchRepositoryImpl(
    private val apiService: MovieApiService
) : SearchRepository {
    override suspend fun search(q: String): Result<List<Movie>> {
        return apiService.search(q).map { list ->
            list.map { dto ->
                Movie(
                    id = dto.id.toString(),
                    title = dto.title,
                    imageUrl = buildImageUrl(dto.poster_path),
                    releaseDate = dto.release_date,
                    averageVote = dto.vote_average,
                    voteCount = dto.vote_count
                )
            }
        }
    }

    // https://image.tmdb.org/t/p/original/{path}
    private fun buildImageUrl(path: String?): String {
        return if (path.isNullOrEmpty()) "" else "https://image.tmdb.org/t/p/original/$path"
    }
}