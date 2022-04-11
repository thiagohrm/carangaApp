package com.example.carangaapp.adapter

import com.example.carangaapp.mainscreen.data.local.CarEntity
import com.example.carangaapp.mainscreen.domain.model.FuelTypeModel.DIESEL
import org.junit.Assert.*

import org.junit.Test

class CarEntityTest {

    private val SUT : CarEntity

    init {
        SUT = CarEntity(
            make = "dummyMarca",
            model = "dummyModelo" ,
            plate = "dummyPlaca",
            year = 1990,
            fuelType = DIESEL
        )
    }

    @Test
    fun checkNotNull(){
        assertNotNull(SUT)
    }
}