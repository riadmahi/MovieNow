package com.riadmahi.movienow.data

import com.riadmahi.movienow.data.model.MoviePageResponse
import com.riadmahi.movienow.utils.Resource
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*

class MovieApi {

    suspend fun getPopularMovies(): Resource<MoviePageResponse> {
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

    suspend fun getNowPlayingMovies(): Resource<MoviePageResponse> {
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

    suspend fun getUpcomingMovies(): Resource<MoviePageResponse> {
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
}