package me.ako.androidbasics.domain.controller

import kotlinx.coroutines.flow.Flow
import me.ako.androidbasics.data.model.*

interface DataRepository {
    fun getUnits(): Flow<List<UnitEntity>>
    fun getUnit(id: Int): Flow<UnitEntity>
    suspend fun insertUnit(unit: UnitEntity)
    suspend fun updateUnit(unit: UnitEntity)
    suspend fun deleteUnit(unit: UnitEntity)
    fun getUnitsWithPathways(): Flow<List<UnitWithPathways>>
    fun getUnitWithPathways(unitId: Int): Flow<UnitWithPathways>
    fun searchUnit(query: String): Flow<List<UnitEntity>>

    fun getPathways(): Flow<List<PathwayEntity>>
    fun getPathway(id: Int): Flow<PathwayEntity>
    suspend fun insertPathway(pathway: PathwayEntity)
    suspend fun updatePathway(pathway: PathwayEntity)
    suspend fun deletePathway(pathway: PathwayEntity)
    fun getPathwayWithActivities(id: Int): Flow<PathwayWithActivities>
    fun getBookmarks(): Flow<List<PathwayEntity>>
    fun searchPathway(query: String): Flow<List<PathwayEntity>>

    fun getActivities(): Flow<List<ActivityEntity>>
    fun getActivity(id: Int): Flow<ActivityEntity>
    suspend fun insertActivity(activity: ActivityEntity)
    suspend fun updateActivity(activity: ActivityEntity)
    suspend fun deleteActivity(activity: ActivityEntity)
    fun searchActivity(query: String): Flow<List<ActivityEntity>>
}