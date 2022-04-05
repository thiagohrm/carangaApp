package com.example.carangaapp.adapter

import com.example.carangaapp.data.CarModel
import org.junit.Assert.*

import org.junit.Test

class CarModelTest {

    private val SUT : CarModel

    init {
        SUT = CarModel(
            make = "dummyMarca",
            model = "dummyModelo" ,
            plate = "dummyPlaca",
            year = 1990
        )
    }

    @Test
    fun checkNotNull(){
        assertNotNull(SUT)
    }
}