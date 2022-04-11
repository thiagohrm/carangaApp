package com.example.carangaapp.mainscreen.data.local

import androidx.room.*

@Dao
interface CarDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCar(carEntity: List<CarEntity>)

    @Delete
    suspend fun deleteCar(carEntity: List<CarEntity>)

    @Query("SELECT * FROM car WHERE id = :id")
    suspend fun getCarById(id: Int): List<CarEntity>?

    @Query("SELECT * FROM car")
    suspend fun getCar(): List<CarEntity>
}