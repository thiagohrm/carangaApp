package com.example.carangaapp.mainscreen.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carangaapp.mainscreen.data.local.CarEntity
import com.example.carangaapp.mainscreen.data.repository.CarRepositoryImpl
import com.example.carangaapp.utils.DispatcherUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarViewModel @Inject constructor(
    private val repository: CarRepositoryImpl,
    private val dispatchers: DispatcherUtils,
) : ViewModel() {
    private val TAG = this::class.qualifiedName

    val carList: LiveData<List<CarEntity>>

    init {
        carList = getCarListFromDb()
    }

    private fun getCarListFromDb(): LiveData<List<CarEntity>> {
        Log.i(TAG, "getCarListFromDb()")
        return repository.getCar()
    }

    fun insertCar(carEntity: CarEntity) {
        Log.i(TAG, "insertCar Initialized")
        viewModelScope.launch(dispatchers.io) {
            val temp = repository.insertCar(carEntity = carEntity)
            Log.i(TAG, "inserCar Sucessfull with id : $temp")
        }
    }

}