package com.example.carangaapp.fueltracking.data.local

import androidx.room.*

@Dao
interface FuelTrackingDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFuelTracking(fuelTrackingEntity: List<FuelTrackingEntity>)

    @Delete
    suspend fun deleteFuelTracking(fuelTrackingEntity: List<FuelTrackingEntity>)

    @Query("SELECT * FROM fueltracking WHERE id = :id")
    suspend fun getFuelTrackingById(id: Int): List<FuelTrackingEntity>?

    @Query("SELECT * FROM fueltracking")
    suspend fun getFuelTracking(): List<FuelTrackingEntity>

}