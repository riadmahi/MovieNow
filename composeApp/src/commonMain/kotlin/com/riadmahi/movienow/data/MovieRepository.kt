package com.riadmahi.movienow.data

import com.riadmahi.movienow.data.model.*
import com.riadmahi.movienow.data.model.local.BookmarkCrossRef
import com.riadmahi.movienow.data.model.local.BookmarkList
import com.riadmahi.movienow.data.model.local.BookmarkWithMovies
import com.riadmahi.movienow.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class MovieRepository(
    private val api: MovieApi,
    private val localDB: LocalDB
) {

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

    fun getTrendingMovieList(): Flow<Resource<MoviePage>> = flow {
        emit(Resource.Loading)
        emit(api.getTrendingMovies())
    }.flowOn(Dispatchers.IO)

    fun getSearchMovieList(query: String): Flow<Resource<MoviePage>> = flow {
        emit(Resource.Loading)
        emit(api.getSearchMovies(query))
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

    fun addMovieToBookmark(movie: MoviePreview): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading)
        emit(localDB.insertMovie(movie))
    }.flowOn(Dispatchers.IO)

    fun deleteMovieToBookmark(movie: MoviePreview): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading)
        emit(localDB.deleteMovie(movie))
    }.flowOn(Dispatchers.IO)

    fun fetchBookmarks(): Flow<Resource<List<MoviePreview>>> = flow {
        emit(Resource.Loading)
        emit(localDB.getBookmarksWithMovies())
    }.flowOn(Dispatchers.IO)
}
