package com.riadmahi.movienow.data

import com.riadmahi.movienow.data.database.MovieNowDatabase
import com.riadmahi.movienow.data.database.dbFileName
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

fun getMovieNowDatabase(): MovieNowDatabase {
    val dbFile = "${documentDirectory()}/$dbFileName"
    return Room.databaseBuilder<MovieNowDatabase>(
        name = dbFile,
    )
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}

@OptIn(ExperimentalForeignApi::class)
private fun documentDirectory(): String {
    val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
      directory = NSDocumentDirectory,
      inDomain = NSUserDomainMask,
      appropriateForURL = null,
      create = false,
      error = null,
    )
    return requireNotNull(documentDirectory?.path)
  }