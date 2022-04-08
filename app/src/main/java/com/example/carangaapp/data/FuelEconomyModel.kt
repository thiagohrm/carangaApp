package com.example.carangaapp.data

import androidx.room.Entity

@Entity(tableName = "fuel-economy")
data class FuelEconomyModel(
    val carUid: Int,
    val odometer: Int,
    val fuelType: FuelTypeModel,
    val average: Float,
)