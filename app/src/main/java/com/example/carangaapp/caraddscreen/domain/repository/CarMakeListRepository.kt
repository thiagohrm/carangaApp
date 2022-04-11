package com.example.carangaapp.caraddscreen.domain.repository

import com.example.carangaapp.caraddscreen.domain.model.carmake.CarMakeListResponse
import com.example.carangaapp.utils.ResourceUtils

interface CarMakeListRepository {
    suspend fun getMakesList() : ResourceUtils<CarMakeListResponse>
}