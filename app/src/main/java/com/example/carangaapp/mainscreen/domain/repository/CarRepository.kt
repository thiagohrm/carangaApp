package com.example.carangaapp.mainscreen.domain.repository

import com.example.carangaapp.mainscreen.domain.model.CarModel

interface CarRepository {
    suspend fun insertCar(carModel : List<CarModel>)
    suspend fun deleteCar(carModel : List<CarModel>)
    suspend fun getCarById(id: Int): List<CarModel>?
    suspend fun getCar(): List<CarModel>
}