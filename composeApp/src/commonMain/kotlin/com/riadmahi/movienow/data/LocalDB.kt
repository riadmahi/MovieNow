package com.riadmahi.movienow.data

import com.riadmahi.movienow.data.database.MovieNowDatabase
import com.riadmahi.movienow.data.model.MoviePreview
import com.riadmahi.movienow.utils.Resource
import kotlinx.coroutines.flow.Flow


class LocalDB(database: MovieNowDatabase) {
    private val dao = database.bookmarkDao()

    suspend fun insertMovie(movie: MoviePreview): Resource<Unit> =
        fetchLocalResource { dao.insertMovie(movie) }

    suspend fun deleteMovie(movie: MoviePreview): Resource<Unit> =
        fetchLocalResource { dao.deleteMovie(movie) }

    fun getAllBookmarksMovie(): Flow<List<MoviePreview>> = dao.getAllMoviesAsFlow()

    private inline fun <T> fetchLocalResource(block: () -> T): Resource<T> {
        return try {
            Resource.Success(block())
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error from the local database.")
        }
    }
}
