package com.example.carangaapp.data.repository.carmake

import com.example.carangaapp.data.models.carmake.CarMakeListResponse
import com.example.carangaapp.utils.ResourceUtils

interface CarMakeListRepo {
    suspend fun getMakesList() : ResourceUtils<CarMakeListResponse>
}