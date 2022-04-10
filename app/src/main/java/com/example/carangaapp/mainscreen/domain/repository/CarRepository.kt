package com.example.carangaapp.mainscreen.domain.repository

import androidx.lifecycle.LiveData
import com.example.carangaapp.mainscreen.data.local.CarEntity

interface CarRepository {
    suspend fun insertCar(carEntity: CarEntity): Long
    suspend fun deleteCar(carEntity: CarEntity)
    suspend fun getCarById(id: Int): CarEntity?
    fun getCar(): LiveData<List<CarEntity>>
}