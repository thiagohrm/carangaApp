package com.example.carangaapp.fueltracking.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.carangaapp.fueltracking.domain.model.FuelTracking
import com.example.carangaapp.fueltracking.domain.repository.FuelTrackingRepository
import com.example.carangaapp.utils.DispatcherUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FuelTrackingViewModel @Inject constructor(
    private val repository: FuelTrackingRepository,
    private val dispatcher: DispatcherUtils,
) : ViewModel() {
    private val TAG = "FuelTrackingViewModel"

    private var _mFuelTrackingList = MutableLiveData<List<FuelTracking>>()
    val fuelTrackingList: LiveData<List<FuelTracking>> = _mFuelTrackingList


    init {
        getListFromDb()
    }

    private fun getListFromDb() {
        viewModelScope.launch(dispatcher.io) {
            _mFuelTrackingList.postValue(repository.getFuelTracking())
        }
    }

    fun insertFuelTrackingOnDb(fuelTracking: List<FuelTracking>) {
        viewModelScope.launch(dispatcher.io) {
            repository.insertFuelTracking(fuelTracking)
        }
    }

}