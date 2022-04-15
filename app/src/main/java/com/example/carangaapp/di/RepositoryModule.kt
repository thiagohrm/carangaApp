package com.example.carangaapp.di

import com.example.carangaapp.fueltracking.data.repository.FuelTrackingRepositoryImpl
import com.example.carangaapp.fueltracking.domain.repository.FuelTrackingRepository
import com.example.carangaapp.mainscreen.data.repository.CarRepositoryImpl
import com.example.carangaapp.mainscreen.domain.repository.CarRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCarRepository(
        carRepositoryImpl: CarRepositoryImpl
    ) : CarRepository

    @Binds
    @Singleton
    abstract fun bindFuelTrackingRepository(
        fuelTrackingRepositoryImpl: FuelTrackingRepositoryImpl
    ) : FuelTrackingRepository
}