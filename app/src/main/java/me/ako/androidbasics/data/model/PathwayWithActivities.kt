package me.ako.androidbasics.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class PathwayWithActivities(
    @Embedded
    val pathway: PathwayEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "pathway_id"
    )
    val activities: List<ActivityEntity>
)