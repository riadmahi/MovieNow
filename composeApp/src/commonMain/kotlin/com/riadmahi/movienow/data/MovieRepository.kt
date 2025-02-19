package com.riadmahi.movienow.data

import com.riadmahi.movienow.data.model.MoviePageResponse
import com.riadmahi.movienow.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class MovieRepository(private val api: MovieApi) {
    fun getPopularMovieList(): Flow<Resource<MoviePageResponse>> = flow {
        emit(Resource.Loading)
        emit(api.getPopularMovies())
    }.flowOn(Dispatchers.IO)

    fun getNowPlayingMovieList(): Flow<Resource<MoviePageResponse>> = flow {
        emit(Resource.Loading)
        emit(api.getNowPlayingMovies())
    }.flowOn(Dispatchers.IO)

    fun getUpcomingMovieList(): Flow<Resource<MoviePageResponse>> = flow {
        emit(Resource.Loading)
        emit(api.getUpcomingMovies())
    }.flowOn(Dispatchers.IO)
}
