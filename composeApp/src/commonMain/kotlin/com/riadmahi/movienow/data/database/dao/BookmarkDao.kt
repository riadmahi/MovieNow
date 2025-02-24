package com.riadmahi.movienow.data.database.dao

import androidx.room.*

import com.riadmahi.movienow.data.model.MoviePreview
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MoviePreview)

    @Delete
    suspend fun deleteMovie(movie: MoviePreview)

    @Query("SELECT * FROM movie_preview")
    fun getAllMoviesAsFlow(): Flow<List<MoviePreview>>
}