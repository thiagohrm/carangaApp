package com.example.carangaapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carangaapp.data.models.CarModel
import com.example.carangaapp.data.repository.CarRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarViewModel @Inject constructor(
    private val repository: CarRepoImpl,
) : ViewModel() {
    private val TAG = this::class.qualifiedName

    val carList: LiveData<List<CarModel>>

    init {
        carList = getCarListFromDb()
    }

    private fun getCarListFromDb(): LiveData<List<CarModel>> {
        Log.i(TAG, "getPoints()")
        return repository.getCar()
    }

    fun insertCar(carModel: CarModel) {
        Log.i(TAG, "insertCar Initialized")
        viewModelScope.launch {
            val temp = repository.insertCar(carModel = carModel)
            Log.i(TAG, "inserCar Sucessfull with id : $temp")
        }
    }

}