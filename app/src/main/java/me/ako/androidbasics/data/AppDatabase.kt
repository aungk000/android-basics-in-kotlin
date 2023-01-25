package me.ako.androidbasics.data

import android.content.Context
import android.util.Log
import androidx.room.TypeConverter
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import me.ako.androidbasics.data.model.*
import org.joda.time.DateTime
import java.util.concurrent.Executor

@Database(
    entities = [UnitEntity::class, PathwayEntity::class, ActivityEntity::class, BookmarkEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(AppDatabase.Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val dao: DataDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        private val queryCallback = object : QueryCallback {
            override fun onQuery(sqlQuery: String, bindArgs: List<Any?>) {
                Log.d("AppDatabase", "SQL query: $sqlQuery, SQL args: $bindArgs")
            }
        }

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_db"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }

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