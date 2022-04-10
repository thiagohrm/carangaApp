package com.example.carangaapp.caraddscreen.data.remote

import com.example.carangaapp.caraddscreen.domain.model.carmodel.CarModelsListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CarModelsListApi {

    @GET("{make}?format=json")
    suspend fun getModelsList(@Path("make") make : String?): Response<CarModelsListResponse>
}