package com.example.carangaapp.mainscreen.data.repository

import android.util.Log
import com.example.carangaapp.mainscreen.data.local.CarDatabase
import com.example.carangaapp.mainscreen.data.mapper.toCarEntity
import com.example.carangaapp.mainscreen.data.mapper.toCarModel
import com.example.carangaapp.mainscreen.domain.model.CarModel
import com.example.carangaapp.mainscreen.domain.repository.CarRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CarRepositoryImpl @Inject constructor(
    db: CarDatabase,
) : CarRepository {
    private val TAG = this::class.qualifiedName

    private val dao = db.dao

    override suspend fun insertCar(carModel: List<CarModel>) {
        dao.insertCar(carEntity = carModel.map {
            it.toCarEntity()
        })
        Log.i(TAG,"insertCar with ${carModel[0].make} - ${carModel[0].model} - ${carModel[0].year}")
    }

    override suspend fun deleteCar(carModel: List<CarModel>) {
        dao.deleteCar(carEntity = carModel.map {
            it.toCarEntity()
        })
        Log.i(TAG,"deleteCar ${carModel[0].id} - ${carModel[0].model}")
    }

    override suspend fun getCarById(id: Int): List<CarModel>? {
        Log.i(TAG,"getCar(${id})")
        val tmp = dao.getCarById(id = id)
        return tmp?.map {
            it.toCarModel()
        }
    }

    override suspend fun getCar(): List<CarModel> {
        Log.i(TAG,"getCar()")
        val response = dao.getCar().map {
            it.toCarModel()
        }
        return response
    }
}