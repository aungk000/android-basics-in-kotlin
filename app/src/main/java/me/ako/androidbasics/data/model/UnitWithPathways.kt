package me.ako.androidbasics.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class UnitWithPathways(
    @Embedded
    val unit: UnitEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "unit_id"
    )
    val pathways: List<PathwayEntity>
)