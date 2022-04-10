package com.example.carangaapp.mainscreen.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carangaapp.mainscreen.domain.model.CarModel
import com.example.carangaapp.mainscreen.domain.repository.CarRepository
import com.example.carangaapp.utils.DispatcherUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarViewModel @Inject constructor(
    private val repository: CarRepository,
    private val dispatchers: DispatcherUtils,
) : ViewModel() {
    private val TAG = this::class.qualifiedName

    private var _carList = MutableLiveData<List<CarModel>>()
    val carList: LiveData<List<CarModel>> = _carList

    init {
       getCarListFromDb()
    }

    private fun getCarListFromDb() {
        Log.i(TAG, "getCarListFromDb()")
        viewModelScope.launch(dispatchers.main) {
           _carList.value = repository.getCar()
        }
    }

    fun insertCar(carModel: List<CarModel>) {
        Log.i(TAG, "insertCar Initialized")
        viewModelScope.launch(dispatchers.io) {
            val temp = repository.insertCar(carModel = carModel)
            Log.i(TAG, "inserCar Sucessfull with id : $temp")
        }
    }

}