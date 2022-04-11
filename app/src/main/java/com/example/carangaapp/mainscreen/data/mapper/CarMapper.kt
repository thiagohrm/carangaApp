package com.example.carangaapp.mainscreen.data.mapper

import com.example.carangaapp.mainscreen.data.local.CarEntity
import com.example.carangaapp.mainscreen.domain.model.CarModel

fun CarEntity.toCarModel() : CarModel {
    return CarModel(
        id = id,
        make = make,
        model = model,
        year = year,
        plate = plate,
        fuelType = fuelType,
        lastAvg = lastAvg
    )
}

fun CarModel.toCarEntity() : CarEntity {
    return CarEntity(
        id = id,
        make = make,
        model = model,
        year = year,
        plate = plate,
        fuelType = fuelType,
        lastAvg = lastAvg
    )
}