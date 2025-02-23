package com.riadmahi.movienow.data

import androidx.room.Room
import androidx.room.RoomDatabase
import com.riadmahi.movienow.data.database.MovieNowDatabase
import com.riadmahi.movienow.data.model.MoviePreview
import com.riadmahi.movienow.data.model.local.BookmarkList
import com.riadmahi.movienow.data.model.local.BookmarkWithMovies
import com.riadmahi.movienow.utils.Resource


class LocalDB(database: MovieNowDatabase) {
    private val dao = database.bookmarkDao()

    suspend fun insertBookmark(bookmark: BookmarkList): Resource<Long> =
        fetchLocalResource { dao.insertBookmark(bookmark) }

    suspend fun insertMovie(movie: MoviePreview): Resource<Unit> =
        fetchLocalResource { dao.insertMovie(movie) }

    suspend fun deleteBookmark(bookmark: BookmarkList): Resource<Unit> =
        fetchLocalResource { dao.deleteBookmark(bookmark) }

    suspend fun getBookmarksWithMovies(): Resource<List<BookmarkWithMovies>> =
        fetchLocalResource { dao.getBookmarksWithMovies() }

    private suspend inline fun <T> fetchLocalResource(block: () -> T): Resource<T> {
        return try {
            Resource.Success(block())
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error from the local database.")
        }
    }
}

