package com.riadmahi.movienow.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.riadmahi.movienow.data.model.local.BookmarkList

@Entity(
    tableName = "movie_preview",
    foreignKeys = [
        ForeignKey(
            entity = BookmarkList::class,
            parentColumns = ["listId"],
            childColumns = ["bookmarkListId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("bookmarkListId")]
)
data class MoviePreview(
    @PrimaryKey val id: Int,
    val title: String,
    val posterPath: String? = null,
    val bookmarkListId: Long? = null
)
