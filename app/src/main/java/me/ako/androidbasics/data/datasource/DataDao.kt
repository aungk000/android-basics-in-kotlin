package me.ako.androidbasics.data.datasource

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import me.ako.androidbasics.data.model.*

@Dao
interface DataDao {
    // unit
    @Query("SELECT * FROM unit")
    fun getUnits(): Flow<List<UnitEntity>>

    @Query("SELECT * FROM unit WHERE id = :id")
    fun getUnit(id: Int): Flow<UnitEntity>

    /*@Query("SELECT * FROM unit WHERE ((title + description) LIKE '%' || :query || '%')")
    fun searchUnit(query: String): Flow<List<UnitEntity>>*/

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUnit(unit: UnitEntity)

    @Update
    suspend fun updateUnit(unit: UnitEntity)

    @Delete
    suspend fun deleteUnit(unit: UnitEntity)

    @Transaction
    @Query("SELECT * FROM unit")
    fun getUnitsWithPathways(): Flow<List<UnitWithPathways>>

    @Transaction
    @Query("SELECT * FROM unit WHERE id = :unitId")
    fun getUnitWithPathways(unitId: Int): Flow<UnitWithPathways>

    // pathway
    @Query("SELECT * FROM pathway")
    fun getPathways(): Flow<List<PathwayEntity>>

    @Query("SELECT * FROM pathway WHERE id = :id")
    fun getPathway(id: Int): Flow<PathwayEntity>

    /*@Query("SELECT * FROM pathway WHERE ((title + description) LIKE '%' || :query || '%')")
    fun searchPathway(query: String): Flow<List<PathwayEntity>>*/

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPathway(pathway: PathwayEntity)

    @Update
    suspend fun updatePathway(pathway: PathwayEntity)

    @Delete
    suspend fun deletePathway(pathway: PathwayEntity)

    @Transaction
    @Query("SELECT * FROM pathway WHERE id = :pathwayId")
    fun getPathwayWithActivities(pathwayId: Int): Flow<PathwayWithActivities>

    @Query("SELECT * FROM pathway WHERE bookmarked = 1")
    fun getBookmarks(): Flow<List<PathwayEntity>>

    // activity
    @Query("SELECT * FROM activity")
    fun getActivities(): Flow<List<ActivityEntity>>

    @Query("SELECT * FROM activity WHERE id = :id")
    fun getActivity(id: Int): Flow<ActivityEntity>

    /*@Query("SELECT * FROM activity WHERE ((title + description) LIKE '%' || :query || '%')")
    fun searchActivity(query: String): Flow<List<ActivityEntity>>*/

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertActivity(activity: ActivityEntity)

    @Update
    suspend fun updateActivity(activity: ActivityEntity)

    @Delete
    suspend fun deleteActivity(activity: ActivityEntity)

    // fts
    @Query("SELECT * FROM unit JOIN unit_fts ON unit.id == unit_fts.rowid WHERE " +
            "unit_fts MATCH '' || :query || '*'")
    fun searchUnit(query: String): Flow<List<UnitEntity>>

    @Query("SELECT * FROM pathway JOIN pathway_fts ON pathway.id == pathway_fts.rowid " +
            "WHERE pathway_fts MATCH '' || :query || '*'")
    fun searchPathway(query: String): Flow<List<PathwayEntity>>

    @Query("SELECT * FROM activity JOIN activity_fts ON activity.id == activity_fts.rowid " +
            "WHERE activity_fts MATCH '' || :query || '*'")
    fun searchActivity(query: String): Flow<List<ActivityEntity>>
}