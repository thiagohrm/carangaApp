package com.example.carangaapp.mainscreen.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.carangaapp.mainscreen.domain.model.FuelTypeModel

@Entity(tableName = "car")
data class CarEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val model: String,
    val make: String,
    val plate: String,
    val year: Int,
    val fuelType: FuelTypeModel,
    val lastAvg: Float = 0f,
)