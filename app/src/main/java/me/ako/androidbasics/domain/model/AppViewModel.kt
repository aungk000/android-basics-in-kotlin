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
    private val _unitsWithPathways = MutableLiveData<List<UnitWithPathways>>()

    val statusUnits: LiveData<Status> get() = _statusUnits
    val unitsWithPathways: LiveData<List<UnitWithPathways>> get() = _unitsWithPathways

    /**
     * adding data in normal function by loop.
     * adding data in suspend function would make ui data inconsistent
     * **/
    fun preloadData(): LiveData<Status> {
        val status = MutableLiveData<Status>()
        status.value = Status.Loading
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

                status.value = Status.Done
            } catch (e: Exception) {
                Log.e("AppViewModel", "preloadData: ${e.message}")
                status.value = Status.Error
            }
        }

        return status
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

    fun loadUnitWithPathways(id: Int): LiveData<UnitWithPathways> {
        return repository.getUnitWithPathways(id).asLiveData()
    }

    fun loadPathwayWithActivities(id: Int): LiveData<PathwayWithActivities> {
        return repository.getPathwayWithActivities(id).asLiveData()
    }

    fun finishActivity(activity: ActivityEntity) {
        activity.finished = true
        viewModelScope.launch {
            repository.updateActivity(activity)
        }
    }

    fun updateProgress(pathway: PathwayEntity, progress: Int) {
        pathway.progress += progress
        viewModelScope.launch {
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