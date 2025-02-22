package com.riadmahi.movienow.data.database.dao

import androidx.room.*
import com.riadmahi.movienow.data.model.MoviePreview
import com.riadmahi.movienow.data.model.local.BookmarkList
import com.riadmahi.movienow.data.model.local.BookmarkWithMovies

@Dao
interface BookmarkDao {

    @Insert
    suspend fun insertBookmark(bookmark: BookmarkList): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MoviePreview)

    @Delete
    suspend fun deleteBookmark(bookmark: BookmarkList)

    @Transaction
    @Query("SELECT * FROM bookmark_list")
    suspend fun getBookmarksWithMovies(): List<BookmarkWithMovies>
}