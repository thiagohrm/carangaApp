package com.example.carangaapp.data.repository.carmodel

import com.example.carangaapp.data.models.carmodel.CarModelsListResponse
import com.example.carangaapp.utils.ResourceUtils

interface CarModelListRepo {

    suspend fun getModelsList(make : String) : ResourceUtils<CarModelsListResponse>
}