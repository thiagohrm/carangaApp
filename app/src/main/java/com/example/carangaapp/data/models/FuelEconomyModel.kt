package com.example.carangaapp.data.models

import androidx.room.Entity
import com.example.carangaapp.data.models.FuelTypeModel

@Entity(tableName = "fuel-economy")
data class FuelEconomyModel(
    val carUid: Int,
    val odometer: Int,
    val fuelType: FuelTypeModel,
    val average: Float,
)