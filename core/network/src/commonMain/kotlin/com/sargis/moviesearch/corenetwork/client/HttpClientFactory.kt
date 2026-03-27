package com.sargis.moviesearch.corenetwork.client

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object HttpClientFactory {
    fun getInstance() = HttpClient {
        install(ContentNegotiation) {
            json(
                Json { ignoreUnknownKeys = true }
            )
        }

        install(HttpTimeout) {
            requestTimeoutMillis = 3000
            socketTimeoutMillis = 3000
            connectTimeoutMillis = 3000
        }

        install(DefaultRequest.Plugin) {
            url {
                host = "api.themoviedb.org"
                protocol = URLProtocol.HTTPS
            }
            header(
                HttpHeaders.Authorization,
                "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4NWUxYjQxZTk3YTA2N2NiYWU1ZjIyNDJkYWQ0ZTNkMCIsIm5iZiI6MTUwNTM5ODA2OS40ODUsInN1YiI6IjU5YmE4ZDM1YzNhMzY4MGQzNDAxMWM5OCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.PauQR78HJwKNjIPLg5WAv7XsuyD9YXKbJXKDLS6pOZA"
            )
        }
    }
}