package com.riadmahi.movienow.data.database.dao

import androidx.room.*
import com.riadmahi.movienow.data.model.Movie
import com.riadmahi.movienow.data.model.MoviePreview

@Dao
interface BookmarkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MoviePreview)

    @Delete
    suspend fun deleteMovie(movie: MoviePreview)

    @Query("SELECT * FROM movie_preview")
    suspend fun getAllMovies(): List<MoviePreview>
}