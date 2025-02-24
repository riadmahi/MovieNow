package com.riadmahi.movienow.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_preview")
data class MoviePreview(
    @PrimaryKey
    val id: Int,
    val title: String,
    val posterPath: String? = null
)
