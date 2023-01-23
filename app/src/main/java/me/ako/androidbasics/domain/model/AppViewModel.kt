package me.ako.androidbasics.domain.model

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import me.ako.androidbasics.data.AppData
import me.ako.androidbasics.data.DataRepository
import me.ako.androidbasics.data.model.ActivityEntity
import me.ako.androidbasics.data.model.PathwayEntity
import me.ako.androidbasics.data.model.PathwayWithActivities
import me.ako.androidbasics.data.model.UnitWithPathways

class AppViewModel(private val repository: DataRepository) : ViewModel() {
    sealed class Status {
        object Loading : Status()
        object Error : Status()
        object Done : Status()
    }

    private val _statusUnits = MutableLiveData<Status>()
    private val _statusPreload = MutableLiveData<Status>()
    private val _unitsWithPathways = MutableLiveData<List<UnitWithPathways>>()
    private val _pathwayWithActivities = MutableLiveData<List<PathwayWithActivities>>()

    val statusUnits: LiveData<Status> get() = _statusUnits
    val statusPreload: LiveData<Status> get() = _statusPreload
    val unitsWithPathways: LiveData<List<UnitWithPathways>> get() = _unitsWithPathways
    val pathwayWithActivities: LiveData<List<PathwayWithActivities>> get() = _pathwayWithActivities

    /**
     * adding data in normal function by loop.
     * adding data in suspend function would make ui data inconsistent
     * **/
    fun preloadData() {
        _statusPreload.value = Status.Loading
        viewModelScope.launch {
            try {
                val data = AppData()
                data.units.forEach {
                    repository.addUnit(it)
                }
                data.pathways.forEach {
                    repository.addPathway(it)
                }
                data.activities.forEach {
                    repository.addActivity(it)
                }

                _statusPreload.value = Status.Done
            } catch (e: Exception) {
                Log.e("AppViewModel", "preloadData: ${e.message}")
                _statusPreload.value = Status.Error
            }
        }
    }

    fun loadUnitsWithPathways() {
        _statusUnits.value = Status.Loading
        viewModelScope.launch {
            try {
                repository.getUnitsWithPathways().collectLatest {
                    _unitsWithPathways.value = it
                    _statusUnits.value = Status.Done
                }
            } catch (e: Exception) {
                Log.e("AppViewModel", "loadUnits: ${e.message}")
                _statusUnits.value = Status.Error
            }
        }
    }

    suspend fun loadUnitWithPathways(id: Int): UnitWithPathways {
        return repository.getUnitWithPathways(id)
    }

    suspend fun loadPathwayWithActivities(id: Int): PathwayWithActivities {
        return repository.getPathwayWithActivities(id)
    }

    fun finishActivity(activity: ActivityEntity) {
        viewModelScope.launch {
            activity.finished = true
            repository.updateActivity(activity)
        }
    }

    fun updateProgress(pathway: PathwayEntity, progress: Int) {
        viewModelScope.launch {
            pathway.progress += progress
            repository.updatePathway(pathway)
        }
    }

    /*private fun retrieveUnitWithPathways(unitId: Int): UnitWithPathways? {
        var pathways: UnitWithPathways? = null
        viewModelScope.launch {
            try {
                pathways = repository.getUnitWithPathways(unitId)
            } catch (e: Exception) {
                Log.e("AppViewModel", "retrievePathways: ${e.message}")
            }
        }
        return pathways
    }*/

    /*fun getBadges(unitId: Int): List<Badge> {
        val badges = arrayListOf<Badge>()
        viewModelScope.launch {
            repository.getPathway(unitId).collect {

            }
        }
    }*/

    /*fun loadingFinished(): LiveData<Boolean> {
        val liveDataMerger = MediatorLiveData<Boolean>()
        liveDataMerger.addSource(statusUnits) {
            liveDataMerger.value = combineLoading(statusUnits, statusPathways)
        }
        liveDataMerger.addSource(statusPathways) {
            liveDataMerger.value = combineLoading(statusUnits, statusPathways)
        }

        return liveDataMerger
    }

    private fun combineLoading(
        unitLoading: LiveData<Status>,
        pathwaysLoading: LiveData<Status>
    ): Boolean {
        return unitLoading.value == Status.Done && pathwaysLoading.value == Status.Done
    }*/

    class Factory(private val repository: DataRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AppViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AppViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel")
        }
    }
}