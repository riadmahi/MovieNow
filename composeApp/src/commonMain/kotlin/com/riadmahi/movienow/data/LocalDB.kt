package com.riadmahi.movienow.data

import androidx.room.Room
import androidx.room.RoomDatabase
import com.riadmahi.movienow.data.database.MovieNowDatabase
import com.riadmahi.movienow.data.model.MoviePreview
import com.riadmahi.movienow.data.model.local.BookmarkList
import com.riadmahi.movienow.data.model.local.BookmarkWithMovies


class LocalDB(database: MovieNowDatabase) {
    val dao = database.bookmarkDao()

    suspend fun insertBookmark(bookmark: BookmarkList): Long = dao.insertBookmark(bookmark)

    suspend fun insertMovie(movie: MoviePreview) = dao.insertMovie(movie)

    suspend fun deleteBookmark(bookmark: BookmarkList) = dao.deleteBookmark(bookmark)

    suspend fun getBookmarksWithMovies(): List<BookmarkWithMovies> = dao.getBookmarksWithMovies()
}

