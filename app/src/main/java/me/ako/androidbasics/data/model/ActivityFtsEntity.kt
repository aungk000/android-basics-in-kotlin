package me.ako.androidbasics.data.model

import androidx.room.*

@Fts4(contentEntity = ActivityEntity::class)
@Entity(tableName = "activity_fts")
data class ActivityFtsEntity(
    val title: String,
    val description: String
)