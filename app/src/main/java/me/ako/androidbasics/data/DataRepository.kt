package me.ako.androidbasics.data

import kotlinx.coroutines.flow.Flow
import me.ako.androidbasics.data.model.*

class DataRepository(private val database: AppDatabase) {
    fun getUnits(): Flow<List<UnitEntity>> = database.dao.getUnits()
    fun getUnit(id: Int): Flow<UnitEntity> = database.dao.getUnit(id)
    suspend fun addUnit(unit: UnitEntity) = database.dao.insertUnit(unit)
    suspend fun updateUnit(unit: UnitEntity) = database.dao.updateUnit(unit)
    suspend fun deleteUnit(unit: UnitEntity) = database.dao.deleteUnit(unit)

    fun getBookmarks(): Flow<List<PathwayEntity>> = database.dao.getBookmarks()
    suspend fun addPathway(pathway: PathwayEntity) = database.dao.insertPathway(pathway)
    suspend fun updatePathway(pathway: PathwayEntity) = database.dao.updatePathway(pathway)
    suspend fun deletePathway(pathway: PathwayEntity) = database.dao.deletePathway(pathway)

    suspend fun addActivity(activity: ActivityEntity) = database.dao.insertActivity(activity)
    suspend fun updateActivity(activity: ActivityEntity) = database.dao.updateActivity(activity)
    suspend fun deleteActivity(activity: ActivityEntity) = database.dao.deleteActivity(activity)

    fun getUnitsWithPathways(): Flow<List<UnitWithPathways>> = database.dao.getUnitsWithPathways()
    fun getUnitWithPathways(unitId: Int): Flow<UnitWithPathways> =
        database.dao.getUnitWithPathways(unitId)

    fun getPathwayWithActivities(pathwayId: Int): Flow<PathwayWithActivities> =
        database.dao.getPathwayWithActivities(pathwayId)
}