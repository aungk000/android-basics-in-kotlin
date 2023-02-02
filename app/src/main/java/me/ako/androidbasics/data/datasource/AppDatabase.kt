package me.ako.androidbasics.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import me.ako.androidbasics.data.model.*
import org.joda.time.DateTime

@Database(
    entities = [
        UnitEntity::class, UnitFtsEntity::class,
        PathwayEntity::class, PathwayFtsEntity::class,
        ActivityEntity::class, ActivityFtsEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(AppDatabase.Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val dao: DataDao

    class Converters {
        @TypeConverter
        fun timestampToDate(time: Long?): DateTime {
            return DateTime(time)
        }

        @TypeConverter
        fun dateToTimestamp(date: DateTime?): Long? {
            return date?.millis
        }
    }
}