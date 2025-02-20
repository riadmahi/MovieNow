package com.riadmahi.movienow.data

import com.riadmahi.movienow.data.model.MovieCredits
import com.riadmahi.movienow.data.model.MovieDetails
import com.riadmahi.movienow.data.model.MoviePage
import com.riadmahi.movienow.data.model.MovieWatchProviders
import com.riadmahi.movienow.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class MovieRepository(private val api: MovieApi) {

    fun getPopularMovieList(): Flow<Resource<MoviePage>> = flow {
        emit(Resource.Loading)
        emit(api.getPopularMovies())
    }.flowOn(Dispatchers.IO)

    fun getNowPlayingMovieList(): Flow<Resource<MoviePage>> = flow {
        emit(Resource.Loading)
        emit(api.getNowPlayingMovies())
    }.flowOn(Dispatchers.IO)

    fun getUpcomingMovieList(): Flow<Resource<MoviePage>> = flow {
        emit(Resource.Loading)
        emit(api.getUpcomingMovies())
    }.flowOn(Dispatchers.IO)

    fun getTopRatedMovieList(): Flow<Resource<MoviePage>> = flow {
        emit(Resource.Loading)
        emit(api.getTopRatedMovies())
    }.flowOn(Dispatchers.IO)

    fun getMovie(id: Int): Flow<Resource<MovieDetails>> = flow {
        emit(Resource.Loading)
        emit(api.getMovie(id = id))
    }.flowOn(Dispatchers.IO)

    fun getMovieWatchProviders(id: Int): Flow<Resource<MovieWatchProviders>> = flow {
        emit(Resource.Loading)
        emit(api.getMovieWatchProviders(id = id))
    }.flowOn(Dispatchers.IO)

    fun getMovieCredits(id: Int): Flow<Resource<MovieCredits>> = flow {
        emit(Resource.Loading)
        emit(api.getMovieCredits(id = id))
    }.flowOn(Dispatchers.IO)
}
