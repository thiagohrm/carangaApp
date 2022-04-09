package com.example.carangaapp.data.repository

import androidx.lifecycle.LiveData
import com.example.carangaapp.data.models.CarModel

interface CarRepo {
    suspend fun insertCar(carModel: CarModel): Long
    suspend fun deleteCar(carModel: CarModel)
    suspend fun getCarById(id: Int): CarModel?
    fun getCar(): LiveData<List<CarModel>>
}