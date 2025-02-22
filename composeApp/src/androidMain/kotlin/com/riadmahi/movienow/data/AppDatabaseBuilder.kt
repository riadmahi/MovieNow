package com.riadmahi.movienow.data

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.riadmahi.movienow.data.database.MovieNowDatabase
import kotlinx.coroutines.Dispatchers


fun getMovieNowDatabaseBuilder(context: Context): MovieNowDatabase {
    val dbFile = context.getDatabasePath("movie_now_local_db.db")
    return Room.databaseBuilder<MovieNowDatabase>(
        context = context.applicationContext,
        name = dbFile.absolutePath
    )
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}