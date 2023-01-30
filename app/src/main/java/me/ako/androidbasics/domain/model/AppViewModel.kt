package me.ako.androidbasics.domain.model

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.ako.androidbasics.data.datasource.AppData
import me.ako.androidbasics.data.model.*
import me.ako.androidbasics.data.repository.DataRepositoryImpl
import me.ako.androidbasics.domain.repository.DataRepository
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(private val repository: DataRepository) : ViewModel() {
    sealed class Status {
        object Loading : Status()
        object Error : Status()
        object Done : Status()
    }

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
                    repository.insertUnit(it)
                }
                data.pathways.forEach {
                    repository.insertPathway(it)
                }
                data.activities.forEach {
                    repository.insertActivity(it)
                }

                status.value = Status.Done
            } catch (e: Exception) {
                Log.e("AppViewModel", "preloadData: ${e.message}")
                status.value = Status.Error
            }
        }

        return status
    }

    fun loadUnitsWithPathways(): LiveData<List<UnitWithPathways>> {
        return repository.getUnitsWithPathways().asLiveData(Dispatchers.IO)
    }

    fun loadUnitWithPathways(id: Int): LiveData<UnitWithPathways> {
        return repository.getUnitWithPathways(id).asLiveData(Dispatchers.IO)
    }

    fun loadPathwayWithActivities(id: Int): LiveData<PathwayWithActivities> {
        return repository.getPathwayWithActivities(id).asLiveData(Dispatchers.IO)
    }

    fun updateActivity(activity: ActivityEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateActivity(activity)
        }
    }

    fun updatePathway(pathway: PathwayEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updatePathway(pathway)
        }
    }

    /*fun loadBookmarks(): LiveData<List<BookmarkWithPathway>> {
        return repository.getBookmarksWithPathway().asLiveData(Dispatchers.IO)
    }*/

    fun loadBookmarks(): LiveData<List<PathwayEntity>> {
        return repository.getBookmarks().asLiveData(Dispatchers.IO)
    }

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

    /*class Factory(private val repository: DataRepositoryImpl) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AppViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AppViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel")
        }
    }*/
}