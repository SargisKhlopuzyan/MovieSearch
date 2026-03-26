package com.sargis.corenetwork.service

import com.sargis.corenetwork.model.MovieDTO
import com.sargis.corenetwork.model.MovieDetailsResponse
import com.sargis.corenetwork.model.SearchResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class MovieApiService(
    private val client: HttpClient
) {
    suspend fun search(q: String): Result<List<MovieDTO>> {
        return try {
            // https://api.themoviedb.org/3/search/movie?query=q
            val searchResponse = client.get("3/search/movie") {
                parameter("query", q)
            }.body<SearchResponse>()
            Result.success(searchResponse.results)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getMovieDetail(movieId: Int): Result<MovieDetailsResponse> {
        return try {
            // https://api.themoviedb.org/3/movie/{movie_id}
            val movieDetailsResponse = client.get("3/movie/$movieId").body<MovieDetailsResponse>()
            Result.success(movieDetailsResponse)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}