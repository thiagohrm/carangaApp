package com.example.carangaapp.mainscreen.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.carangaapp.mainscreen.data.local.CarDAO
import com.example.carangaapp.mainscreen.data.local.CarEntity
import com.example.carangaapp.mainscreen.domain.repository.CarRepository
import javax.inject.Inject


class CarRepositoryImpl @Inject constructor(
    private val dao: CarDAO,
) : CarRepository {
    private val TAG = this::class.qualifiedName

    override suspend fun insertCar(carEntity: CarEntity): Long {
        val tmp = dao.insertCar(carEntity = carEntity)
        Log.i(TAG,"insertCar id $tmp - ${carEntity.model}")
        return tmp
    }

    override suspend fun deleteCar(carEntity: CarEntity) {
        dao.deleteCar(carEntity = carEntity)
        Log.i(TAG,"deleteCar ${carEntity.id} - ${carEntity.model}")
    }

    override suspend fun getCarById(id: Int): CarEntity? {
        Log.i(TAG,"getCar(${id})")
        return dao.getCarById(id = id)
    }

    override fun getCar(): LiveData<List<CarEntity>> {
        Log.i(TAG,"getCar()")
        return dao.getCar()
    }
}