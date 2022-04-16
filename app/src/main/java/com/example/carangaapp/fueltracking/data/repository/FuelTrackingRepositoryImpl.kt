package com.example.carangaapp.fueltracking.data.repository

import com.example.carangaapp.fueltracking.data.local.FuelTrackingDatabase
import com.example.carangaapp.fueltracking.data.mapper.toFuelTracking
import com.example.carangaapp.fueltracking.data.mapper.toFuelTrackingEntity
import com.example.carangaapp.fueltracking.domain.model.FuelTracking
import com.example.carangaapp.fueltracking.domain.repository.FuelTrackingRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FuelTrackingRepositoryImpl @Inject constructor(
    db: FuelTrackingDatabase,
) : FuelTrackingRepository {
    private val dao = db.dao

    override suspend fun insertFuelTracking(fuelTracking: List<FuelTracking>) {
        dao.insertFuelTracking(fuelTracking.map {
            it.toFuelTrackingEntity()
        })
    }

    override suspend fun deleteFuelTracking(fuelTracking: List<FuelTracking>) {
        dao.deleteFuelTracking(fuelTracking.map {
            it.toFuelTrackingEntity()
        })
    }

    override suspend fun getFuelTrackingById(id: Int): List<FuelTracking>? {
        val response = dao.getFuelTrackingById(id)
        return response?.let {
            it.map {
                it.toFuelTracking()
            }
        }
    }

    override suspend fun getFuelTracking(): List<FuelTracking> {
        val response = dao.getFuelTracking()
        return response.map {
            it.toFuelTracking()
        }
    }
}
