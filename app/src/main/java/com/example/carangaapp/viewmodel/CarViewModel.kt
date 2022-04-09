package com.example.carangaapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carangaapp.data.models.CarModel
import com.example.carangaapp.data.repository.CarRepoImpl
import com.example.carangaapp.utils.DispatcherUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarViewModel @Inject constructor(
    private val repository: CarRepoImpl,
    private val dispatchers: DispatcherUtils,
) : ViewModel() {
    private val TAG = this::class.qualifiedName

    val carList: LiveData<List<CarModel>>

    init {
        carList = getCarListFromDb()
    }

    private fun getCarListFromDb(): LiveData<List<CarModel>> {
        Log.i(TAG, "getCarListFromDb()")
        return repository.getCar()
    }

    fun insertCar(carModel: CarModel) {
        Log.i(TAG, "insertCar Initialized")
        viewModelScope.launch(dispatchers.io) {
            val temp = repository.insertCar(carModel = carModel)
            Log.i(TAG, "inserCar Sucessfull with id : $temp")
        }
    }

}