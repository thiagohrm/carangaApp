package com.example.carangaapp.mainscreen.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CarDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCar(carEntity: CarEntity): Long

    @Delete
    suspend fun deleteCar(carEntity: CarEntity)

    @Query("SELECT * FROM car WHERE id = :id")
    suspend fun getCarById(id: Int): CarEntity?

    @Query("SELECT * FROM car")
    fun getCar(): LiveData<List<CarEntity>>
}