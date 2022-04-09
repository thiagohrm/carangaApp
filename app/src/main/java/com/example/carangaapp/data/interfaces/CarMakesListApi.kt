package com.example.carangaapp.data.interfaces

import com.example.carangaapp.data.models.carmake.CarMakeListResponse
import retrofit2.Response
import retrofit2.http.GET

interface CarMakesListApi {

    @GET("getallmakes?format=json")
    suspend fun getMakeList():Response<CarMakeListResponse>
}