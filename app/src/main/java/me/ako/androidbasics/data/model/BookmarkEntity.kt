package me.ako.androidbasics.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import org.joda.time.DateTime

@Entity(
    tableName = "bookmark",
    foreignKeys = [
        ForeignKey(
            entity = PathwayEntity::class,
            parentColumns = ["id"],
            childColumns = ["pathway_id"]
        )
    ]
)
data class BookmarkEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("pathway_id", index = true)
    val pathwayId: Int,
    @ColumnInfo(name = "datetime")
    val dateTime: DateTime
)