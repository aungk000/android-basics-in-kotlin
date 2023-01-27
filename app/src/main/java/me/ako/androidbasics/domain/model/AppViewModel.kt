package me.ako.androidbasics.domain.model

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.ako.androidbasics.AndroidBasicsApplication
import me.ako.androidbasics.data.AppData
import me.ako.androidbasics.data.DataRepository
import me.ako.androidbasics.data.model.*

class AppViewModel(private val repository: DataRepository) : ViewModel() {
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

    fun loadUnitsWithPathways(): LiveData<List<UnitWithPathways>> {
        return repository.getUnitsWithPathways().asLiveData(Dispatchers.IO)
    }

    fun loadUnitWithPathways(id: Int): LiveData<UnitWithPathways> {
        return repository.getUnitWithPathways(id).asLiveData(Dispatchers.IO)
    }

    fun loadPathwayWithActivities(id: Int): LiveData<PathwayWithActivities> {
        return repository.getPathwayWithActivities(id).asLiveData(Dispatchers.IO)
    }

    fun finishActivity(activity: ActivityEntity, finished: Boolean) {
        activity.finished = finished
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateActivity(activity)
        }
    }

    fun updateProgress(pathway: PathwayEntity, progress: Int) {
        pathway.progress += progress
        viewModelScope.launch(Dispatchers.IO) {
            repository.updatePathway(pathway)
        }
    }

    fun updateBookmarked(pathway: PathwayEntity, bookmarked: Boolean) {
        pathway.bookmarked = bookmarked
        viewModelScope.launch(Dispatchers.IO) {
            repository.updatePathway(pathway)
        }
    }

    fun loadBookmarks(): LiveData<List<BookmarkWithPathway>> {
        return repository.getBookmarksWithPathway().asLiveData(Dispatchers.IO)
    }

    fun addBookmark(bookmark: BookmarkEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addBookmark(bookmark)
        }
    }

    fun deleteBookmark(pathwayId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteBookmark(pathwayId)
        }
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