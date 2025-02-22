package com.riadmahi.movienow.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark_list")
data class BookmarkList(
    @PrimaryKey(autoGenerate = true) val listId: Long = 0,
    val name: String
)
