package com.sargis.moviesearch.core.data.api

import com.sargis.moviesearch.core.data.dto.MovieDetailsResponseDto
import com.sargis.moviesearch.core.data.dto.SearchResponseDto
import com.sargis.moviesearch.core.data.util.safeCall
import com.sargis.moviesearch.core.domain.DataError
import com.sargis.moviesearch.core.domain.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class KtorRemoteMovieDataSource(
    private val httpClient: HttpClient
) : RemoteMovieDataSource {
    override suspend fun searchMovie(q: String): Result<SearchResponseDto, DataError.Remote> {
        return safeCall<SearchResponseDto> {
            // https://api.themoviedb.org/3/search/movie?query=q
            httpClient.get("3/search/movie") {
                parameter("query", q)
            }.body()
        }
    }

    override suspend fun getMovieDetail(movieId: Int): Result<MovieDetailsResponseDto, DataError.Remote> {
        return safeCall {
            // https://api.themoviedb.org/3/movie/{movie_id}
            httpClient.get("3/movie/$movieId").body()
        }
    }
}