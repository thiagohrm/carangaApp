package com.example.carangaapp.fueltracking.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.carangaapp.mainscreen.domain.model.FuelTypeModel

@Entity(tableName = "fueltracking")
data class FuelTrackingEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val carId : Int,
    val fuelType: FuelTypeModel,
    val odometer: Int,
    val price: Float,
    val amount: Float,
    val volume: Float,
)