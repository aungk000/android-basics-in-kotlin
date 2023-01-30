package me.ako.androidbasics.data.repository

import kotlinx.coroutines.flow.Flow
import me.ako.androidbasics.data.datasource.DataDao
import me.ako.androidbasics.data.model.*
import me.ako.androidbasics.domain.repository.DataRepository

class DataRepositoryImpl(private val dao: DataDao): DataRepository {
    override fun getUnits(): Flow<List<UnitEntity>> {
        return dao.getUnits()
    }

    override fun getUnit(id: Int): Flow<UnitEntity> {
        return dao.getUnit(id)
    }

    override suspend fun insertUnit(unit: UnitEntity) {
        dao.insertUnit(unit)
    }

    override suspend fun updateUnit(unit: UnitEntity) {
        dao.updateUnit(unit)
    }

    override suspend fun deleteUnit(unit: UnitEntity) {
        dao.deleteUnit(unit)
    }

    override fun getUnitsWithPathways(): Flow<List<UnitWithPathways>> {
        return dao.getUnitsWithPathways()
    }

    override fun getUnitWithPathways(unitId: Int): Flow<UnitWithPathways> {
        return dao.getUnitWithPathways(unitId)
    }

    override fun getPathways(): Flow<List<PathwayEntity>> {
        return dao.getPathways()
    }

    override fun getPathway(id: Int): Flow<PathwayEntity> {
        return dao.getPathway(id)
    }

    override suspend fun insertPathway(pathway: PathwayEntity) {
        dao.insertPathway(pathway)
    }

    override suspend fun updatePathway(pathway: PathwayEntity) {
        dao.updatePathway(pathway)
    }

    override suspend fun deletePathway(pathway: PathwayEntity) {
        dao.deletePathway(pathway)
    }

    override fun getPathwayWithActivities(id: Int): Flow<PathwayWithActivities> {
        return dao.getPathwayWithActivities(id)
    }

    override fun getBookmarks(): Flow<List<PathwayEntity>> {
        return dao.getBookmarks()
    }

    override fun getActivities(): Flow<List<ActivityEntity>> {
        return dao.getActivities()
    }

    override fun getActivity(id: Int): Flow<ActivityEntity> {
        return dao.getActivity(id)
    }

    override suspend fun insertActivity(activity: ActivityEntity) {
        dao.insertActivity(activity)
    }

    override suspend fun updateActivity(activity: ActivityEntity) {
        dao.updateActivity(activity)
    }

    override suspend fun deleteActivity(activity: ActivityEntity) {
        dao.deleteActivity(activity)
    }
}