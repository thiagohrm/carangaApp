package com.example.carangaapp.caraddscreen.domain.repository

import com.example.carangaapp.caraddscreen.domain.model.carmodel.CarModelsListResponse
import com.example.carangaapp.utils.ResourceUtils

interface CarModelListRepository {

    suspend fun getModelsList(make : String) : ResourceUtils<CarModelsListResponse>
}