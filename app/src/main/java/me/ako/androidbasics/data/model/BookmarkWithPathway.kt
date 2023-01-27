package me.ako.androidbasics.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class BookmarkWithPathway(
    @Embedded
    val bookmark: BookmarkEntity,
    @Relation(
        parentColumn = "pathway_id",
        entityColumn = "id"
    )
    val pathway: PathwayEntity
)