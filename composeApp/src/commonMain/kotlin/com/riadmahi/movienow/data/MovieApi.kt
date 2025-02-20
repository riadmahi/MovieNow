package com.riadmahi.movienow.data

import com.riadmahi.movienow.data.model.MovieDetails
import com.riadmahi.movienow.data.model.MoviePage
import com.riadmahi.movienow.utils.Resource
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*

class MovieApi {

    suspend fun getPopularMovies(): Resource<MoviePage> {
        try {
            val response = client.get {
                url("movie/popular?language=en-US&page=1")
            }
            return Resource.Success(response.body())
        } catch (e: RedirectResponseException) {
            return (Resource.Error(e.response.status.description))
        } catch (e: ClientRequestException) {
            return (Resource.Error(e.response.status.description))
        } catch (e: ServerResponseException) {
            return (Resource.Error(e.response.status.description))
        } catch (e: Exception) {
            return (Resource.Error(e.message ?: "Something went wrong"))
        }
    }

    suspend fun getNowPlayingMovies(): Resource<MoviePage> {
        try {
            val response = client.get {
                url("movie/now_playing?language=en-US&page=1")
            }
            return Resource.Success(response.body())
        } catch (e: RedirectResponseException) {
            return (Resource.Error(e.response.status.description))
        } catch (e: ClientRequestException) {
            return (Resource.Error(e.response.status.description))
        } catch (e: ServerResponseException) {
            return (Resource.Error(e.response.status.description))
        } catch (e: Exception) {
            return (Resource.Error(e.message ?: "Something went wrong"))
        }
    }

    suspend fun getUpcomingMovies(): Resource<MoviePage> {
        try {
            val response = client.get {
                url("movie/upcoming?language=en-US&page=1")
            }
            return Resource.Success(response.body())
        } catch (e: RedirectResponseException) {
            return (Resource.Error(e.response.status.description))
        } catch (e: ClientRequestException) {
            return (Resource.Error(e.response.status.description))
        } catch (e: ServerResponseException) {
            return (Resource.Error(e.response.status.description))
        } catch (e: Exception) {
            return (Resource.Error(e.message ?: "Something went wrong"))
        }
    }

    suspend fun getMovie(id: Int): Resource<MovieDetails> {
        try {
            val response = client.get {
                url("movie/$id?language=en-US&page=1")
            }
            return Resource.Success(response.body())
        } catch (e: RedirectResponseException) {
            return (Resource.Error(e.response.status.description))
        } catch (e: ClientRequestException) {
            return (Resource.Error(e.response.status.description))
        } catch (e: ServerResponseException) {
            return (Resource.Error(e.response.status.description))
        } catch (e: Exception) {
            return (Resource.Error(e.message ?: "Something went wrong"))
        }
    }
}