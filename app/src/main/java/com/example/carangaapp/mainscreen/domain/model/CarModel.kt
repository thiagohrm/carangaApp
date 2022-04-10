package com.example.carangaapp.mainscreen.domain.model

data class CarModel(
    val model: String,
    val make: String,
    val plate: String,
    val year: Int,
    val fuelType: FuelTypeModel,
    val lastAvg: Float = 0f,
)
