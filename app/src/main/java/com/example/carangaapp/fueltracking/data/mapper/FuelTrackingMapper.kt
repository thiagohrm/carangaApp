package com.example.carangaapp.fueltracking.data.mapper

import com.example.carangaapp.fueltracking.data.local.FuelTrackingEntity
import com.example.carangaapp.fueltracking.domain.model.FuelTracking

fun FuelTracking.toFuelTrackingEntity(): FuelTrackingEntity {
    return FuelTrackingEntity(
        id, carId, fuelType, odometer, price, amount, volume
    )
}

fun FuelTrackingEntity.toFuelTracking(): FuelTracking {
    return FuelTracking(
        id, carId, fuelType, odometer, price, amount, volume
    )
}