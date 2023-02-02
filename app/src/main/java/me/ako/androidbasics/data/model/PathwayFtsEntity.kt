package me.ako.androidbasics.data.model

import androidx.room.*

@Fts4(contentEntity = PathwayEntity::class)
@Entity(tableName = "pathway_fts")
data class PathwayFtsEntity(
    val title: String,
    val description: String
)