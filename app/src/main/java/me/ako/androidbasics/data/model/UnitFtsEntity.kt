package me.ako.androidbasics.data.model

import androidx.room.*

@Fts4(contentEntity = UnitEntity::class)
@Entity(tableName = "unit_fts")
data class UnitFtsEntity(
    val title: String,
    val description: String
)