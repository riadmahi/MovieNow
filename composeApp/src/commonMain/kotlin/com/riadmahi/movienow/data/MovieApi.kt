package com.riadmahi.movienow.data

import com.riadmahi.movienow.data.model.MovieDetails
import com.riadmahi.movienow.data.model.MoviePage
import com.riadmahi.movienow.data.model.MovieWatchProviders
import com.riadmahi.movienow.utils.Resource
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*

class MovieApi {
    suspend fun getPopularMovies(): Resource<MoviePage> =
        fetchResource("movie/popular?language=en-US&page=1")

    suspend fun getNowPlayingMovies(): Resource<MoviePage> =
        fetchResource("movie/now_playing?language=en-US&page=1")

    suspend fun getUpcomingMovies(): Resource<MoviePage> =
        fetchResource("movie/upcoming?language=en-US&page=1")

    suspend fun getMovie(id: Int): Resource<MovieDetails> =
        fetchResource("movie/$id?language=en-US&page=1")

    suspend fun getMovieWatchProviders(id: Int): Resource<MovieWatchProviders> =
        fetchResource("movie/$id/watch/providers")

    private suspend inline fun <reified T> fetchResource(endpoint: String): Resource<T> {
        return try {
            val response = client.get {
                url(endpoint)
            }
            Resource.Success(response.body())
        } catch (e: Exception) {
            val errorMsg = when (e) {
                is RedirectResponseException,
                is ClientRequestException,
                    is ServerResponseException -> e.message ?: "Server error"
                else -> e.message ?: "Something went wrong"
            }
            Resource.Error(errorMsg)
        }
    }
}