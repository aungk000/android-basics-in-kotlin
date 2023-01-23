package me.ako.androidbasics.data.model

import android.net.Uri
import androidx.room.*

@Entity(
    tableName = "activity",
    foreignKeys = [
        ForeignKey(
            entity = PathwayEntity::class,
            childColumns = ["pathway_id"],
            parentColumns = ["id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
@TypeConverters(ActivityTypeConverter::class)
data class ActivityEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "pathway_id", index = true)
    val pathwayId: Int,
    @ColumnInfo(name = "number")
    val number: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "type")
    val type: ActivityType = ActivityType.CodeLab,
    @ColumnInfo(name = "optional")
    val optional: Boolean = false,
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "progress")
    val progress: Int = 0,
    @ColumnInfo(name = "finished")
    var finished: Boolean = false
) {
    /**
     * to generate YouTube app links
     */
    val launchUri: Uri
        get() {
            val httpUri = Uri.parse(url)
            return Uri.parse("vnd.youtube:" + httpUri.getQueryParameter("v"))
        }
}

sealed class ActivityType(val type: String) {
    object CodeLab: ActivityType("CodeLab")
    object Video: ActivityType("Video")
    object Article: ActivityType("Article")
    object Quiz: ActivityType("Quiz")
    object Unknown: ActivityType("Unknown")
}

class ActivityTypeConverter {
    @TypeConverter
    fun typeToInt(type: ActivityType?): Int {
        return when (type) {
            ActivityType.CodeLab -> {
                1
            }
            ActivityType.Video -> {
                2
            }
            ActivityType.Article -> {
                3
            }
            ActivityType.Quiz -> {
                4
            }
            else -> {
                0
            }
        }
    }

    @TypeConverter
    fun intToType(type: Int?): ActivityType {
        return when (type) {
            1 -> {
                ActivityType.CodeLab
            }
            2 -> {
                ActivityType.Video
            }
            3 -> {
                ActivityType.Article
            }
            4 -> {
                ActivityType.Quiz
            }
            else -> {
                ActivityType.Unknown
            }
        }
    }
}