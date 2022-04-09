package com.example.carangaapp.data.repository

import com.example.carangaapp.data.models.CarMakeListResponse
import com.example.carangaapp.utils.ResourceUtils

interface CarMakeListRepo {
    suspend fun getMakesList() : ResourceUtils<CarMakeListResponse>
}