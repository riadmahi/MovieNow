package com.riadmahi.movienow.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.riadmahi.movienow.data.database.dao.BookmarkDao
import com.riadmahi.movienow.data.model.MoviePreview

@Database(
    entities = [MoviePreview::class],
    version = 1
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class MovieNowDatabase: RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkDao
}


@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<MovieNowDatabase> {
    override fun initialize(): MovieNowDatabase
}

internal const val dbFileName = "local_movie_now.db"
