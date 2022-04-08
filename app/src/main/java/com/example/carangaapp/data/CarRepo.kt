package com.example.carangaapp.data

import androidx.lifecycle.LiveData

interface CarRepo {
    suspend fun insertCar(carModel: CarModel): Long
    suspend fun deleteCar(carModel: CarModel)
    suspend fun getCarById(id: Int): CarModel?
    fun getCar(): LiveData<List<CarModel>>
}