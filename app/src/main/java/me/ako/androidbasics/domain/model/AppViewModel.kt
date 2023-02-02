package me.ako.androidbasics.domain.model

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.ako.androidbasics.data.datasource.AppData
import me.ako.androidbasics.data.model.*
import me.ako.androidbasics.domain.controller.DataRepository
import me.ako.androidbasics.presentation.model.*
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(private val repository: DataRepository) : ViewModel() {
    private val _searchData = CombinedLiveData()
    val searchData get() = _searchData

    init {
        Log.d("AppViewModel", "ViewModel created")
    }

    override fun onCleared() {
        Log.d("AppViewModel", "ViewModel destroyed")
        super.onCleared()
    }

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

    class CombinedLiveData : MediatorLiveData<List<Search>>() {
        private var data1: List<UnitEntity>? = null
        private var data2: List<PathwayEntity>? = null
        private var data3: List<ActivityEntity>? = null

        fun addSources(
            source1: LiveData<List<UnitEntity>>,
            source2: LiveData<List<PathwayEntity>>,
            source3: LiveData<List<ActivityEntity>>
        ) {
            super.addSource(source1) {
                data1 = it
                value = combineListData(data1, data2, data3)
            }
            super.addSource(source2) {
                data2 = it
                value = combineListData(data1, data2, data3)
            }
            super.addSource(source3) {
                data3 = it
                value = combineListData(data1, data2, data3)
            }
        }

        private fun combineListData(
            units: List<UnitEntity>?,
            pathways: List<PathwayEntity>?,
            activities: List<ActivityEntity>?
        ): List<Search> {
            val listData = mutableListOf<Search>()
            if (units != null && units.isNotEmpty()) {
                listData.add(SearchHeader(SearchHeader.Type.Unit))
                listData.addAll(
                    units.map {
                        SearchUnit(it)
                    }
                )
            }
            if (pathways != null && pathways.isNotEmpty()) {
                listData.add(SearchHeader(SearchHeader.Type.Pathway))
                listData.addAll(
                    pathways.map {
                        SearchPathway(it)
                    }
                )
            }
            if (activities != null && activities.isNotEmpty()) {
                listData.add(SearchHeader(SearchHeader.Type.Activity))
                listData.addAll(
                    activities.map {
                        SearchActivity(it)
                    }
                )
            }

            return listData
        }
    }

    fun searchDb(query: String) {
        val units = repository.searchUnit(query).asLiveData(Dispatchers.IO)
        val pathways = repository.searchPathway(query).asLiveData(Dispatchers.IO)
        val activities = repository.searchActivity(query).asLiveData(Dispatchers.IO)

        _searchData.addSources(units, pathways, activities)
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