package com.riadmahi.movienow.data

import com.riadmahi.movienow.data.model.MovieCredits
import com.riadmahi.movienow.data.model.MovieDetails
import com.riadmahi.movienow.data.model.MoviePage
import com.riadmahi.movienow.data.model.MovieWatchProviders
import com.riadmahi.movienow.utils.Resource
import com.riadmahi.movienow.utils.getDefaultLanguage
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*

class MovieApi {
    private val defaultLanguage = getDefaultLanguage()

    suspend fun getPopularMovies(): Resource<MoviePage> =
        fetchResource("movie/popular?language=${defaultLanguage}&page=1")

    suspend fun getNowPlayingMovies(): Resource<MoviePage> =
        fetchResource("movie/now_playing?language=${defaultLanguage}S&page=1")

    suspend fun getUpcomingMovies(): Resource<MoviePage> =
        fetchResource("movie/upcoming?language=${defaultLanguage}&page=1")

    suspend fun getTopRatedMovies(): Resource<MoviePage> =
        fetchResource("movie/top_rated?language=${defaultLanguage}&page=1")
    
    suspend fun getSearchMovies(query: String): Resource<MoviePage> =
        fetchResource("search/movie?query=$query&include_adult=false&language=${defaultLanguage}&page=1")
    suspend fun getTrendingMovies(): Resource<MoviePage> =
        fetchResource("trending/movie/day?${defaultLanguage}&page=1")

    suspend fun getMovie(id: Int): Resource<MovieDetails> =
        fetchResource("movie/$id?language=${defaultLanguage}&page=1")

    suspend fun getMovieWatchProviders(id: Int): Resource<MovieWatchProviders> =
        fetchResource("movie/$id/watch/providers")

    suspend fun getMovieCredits(id: Int): Resource<MovieCredits> =
        fetchResource("movie/$id/credits?language=${defaultLanguage}")

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