package com.riadmahi.movienow.data.model.local

import androidx.room.Embedded
import androidx.room.Relation
import com.riadmahi.movienow.data.model.MoviePreview

data class BookmarkWithMovies(
    @Embedded val bookmark: BookmarkList,
    @Relation(
        parentColumn = "listId",
        entityColumn = "bookmarkListId"
    )
    val movies: List<MoviePreview>
)