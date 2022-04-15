package com.example.carangaapp.fueltracking.domain.repository

import com.example.carangaapp.fueltracking.domain.model.FuelTracking

interface FuelTrackingRepository {

    suspend fun insertFuelTracking(fuelTracking: List<FuelTracking>)
    suspend fun deleteFuelTracking(fuelTracking: List<FuelTracking>)
    suspend fun getFuelTrackingById(id: Int): List<FuelTracking>?
    suspend fun getFuelTracking(): List<FuelTracking>

}