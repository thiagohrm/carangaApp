package com.example.carangaapp.adapter

import org.junit.Assert.*

import org.junit.Test

class PrincipalDataClassTest {

    private val SUT : PrincipalDataClass

    init {
        SUT = PrincipalDataClass(
            marca = "dummyMarca",
            modelo = "dummyModelo" ,
            placa = "dummyPlaca",
            ano = 1990
        )
    }

    @Test
    fun checkNotNull(){
        assertNotNull(SUT)
    }
}