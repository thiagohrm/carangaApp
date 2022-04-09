package com.example.carangaapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "car")
data class CarModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val model: String,
    val make: String,
    val plate: String,
    val year: Int,
    val fuelType: FuelTypeModel,
    val lastAvg: Float = 0f,
)