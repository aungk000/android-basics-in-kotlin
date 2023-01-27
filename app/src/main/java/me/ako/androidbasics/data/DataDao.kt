package me.ako.androidbasics.data

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import me.ako.androidbasics.data.model.*

@Dao
interface DataDao {
    @Query("SELECT * FROM unit WHERE id = :id")
    fun getUnit(id: Int): Flow<UnitEntity>

    @Query("SELECT * FROM unit")
    fun getUnits(): Flow<List<UnitEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUnit(unit: UnitEntity)

    @Update
    suspend fun updateUnit(unit: UnitEntity)

    @Delete
    suspend fun deleteUnit(unit: UnitEntity)

    @Query("SELECT * FROM pathway WHERE bookmarked = 1")
    fun getBookmarks(): Flow<List<PathwayEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPathway(pathway: PathwayEntity)

    @Update
    suspend fun updatePathway(pathway: PathwayEntity)

    @Delete
    suspend fun deletePathway(pathway: PathwayEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertActivity(activity: ActivityEntity)

    @Update
    suspend fun updateActivity(activity: ActivityEntity)

    @Delete
    suspend fun deleteActivity(activity: ActivityEntity)

    @Transaction
    @Query("SELECT * FROM unit")
    fun getUnitsWithPathways(): Flow<List<UnitWithPathways>>

    @Transaction
    @Query("SELECT * FROM unit WHERE id = :unitId")
    fun getUnitWithPathways(unitId: Int): Flow<UnitWithPathways>

    @Transaction
    @Query("SELECT * FROM pathway WHERE id = :pathwayId")
    fun getPathwayWithActivities(pathwayId: Int): Flow<PathwayWithActivities>
}