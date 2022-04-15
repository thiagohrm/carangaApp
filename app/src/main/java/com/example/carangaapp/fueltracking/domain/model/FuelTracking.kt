package com.example.carangaapp.fueltracking.domain.model

import com.example.carangaapp.mainscreen.domain.model.FuelTypeModel

data class FuelTracking(
    val id: Int = 0,
    val carId : Int,
    val fuelType: FuelTypeModel,
    val odometer: Int,
    val price : Float,
    val amount : Float,
    val volume : Float
)
