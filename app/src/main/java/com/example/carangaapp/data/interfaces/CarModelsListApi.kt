package com.example.carangaapp.data.interfaces

import com.example.carangaapp.data.models.carmodel.CarModelsListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CarModelsListApi {

    @GET("{make}?format=json")
    suspend fun getModelsList(@Path("make") make : String?): Response<CarModelsListResponse>
}