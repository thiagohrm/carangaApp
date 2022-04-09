package com.example.carangaapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.carangaapp.data.dao.CarDAO
import com.example.carangaapp.data.models.CarModel
import javax.inject.Inject


class CarRepoImpl @Inject constructor(
    private val dao: CarDAO,
) : CarRepo {
    private val TAG = this::class.qualifiedName

    override suspend fun insertCar(carModel: CarModel): Long {
        val tmp = dao.insertCar(carModel = carModel)
        Log.i(TAG,"insertCar id $tmp - ${carModel.model}")
        return tmp
    }

    override suspend fun deleteCar(carModel: CarModel) {
        dao.deleteCar(carModel = carModel)
        Log.i(TAG,"deleteCar ${carModel.id} - ${carModel.model}")
    }

    override suspend fun getCarById(id: Int): CarModel? {
        Log.i(TAG,"getCar(${id})")
        return dao.getCarById(id = id)
    }

    override fun getCar(): LiveData<List<CarModel>> {
        Log.i(TAG,"getCar()")
        return dao.getCar()
    }
}