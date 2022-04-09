package com.example.carangaapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.carangaapp.data.models.CarModel

@Dao
interface CarDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCar(carModel: CarModel): Long

    @Delete
    suspend fun deleteCar(carModel: CarModel)

    @Query("SELECT * FROM car WHERE id = :id")
    suspend fun getCarById(id: Int): CarModel?

    @Query("SELECT * FROM car")
    fun getCar(): LiveData<List<CarModel>>
}