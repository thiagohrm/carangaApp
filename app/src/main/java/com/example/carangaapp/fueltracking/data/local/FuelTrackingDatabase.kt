package com.example.carangaapp.fueltracking.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FuelTrackingEntity::class],
    version = 2
)
abstract class FuelTrackingDatabase : RoomDatabase() {
    abstract val dao: FuelTrackingDAO
}