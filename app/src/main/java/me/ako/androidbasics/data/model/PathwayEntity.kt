package me.ako.androidbasics.data.model

import androidx.room.*
import org.joda.time.DateTime

@Entity(
    tableName = "pathway",
    foreignKeys = [
        ForeignKey(
            entity = UnitEntity::class,
            childColumns = ["unit_id"],
            parentColumns = ["id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class PathwayEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "unit_id", index = true)
    val unitId: Int,
    @ColumnInfo(name = "number")
    val number: Int,
    @ColumnInfo(name = "image")
    val image: Int,
    @ColumnInfo(name = "activities")
    val activities: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "datetime")
    val datetime: DateTime,
    @ColumnInfo(name = "progress")
    var progress: Int = 0,
    @ColumnInfo(name = "bookmarked")
    var bookmarked: Boolean = false
) {
    val progressCompleted: String get() =  "${progress}% completed"
}
