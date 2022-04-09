package com.example.carangaapp.data

import com.example.carangaapp.data.models.CarMakeListRespose
import retrofit2.Response
import retrofit2.http.GET

interface CarMakesListApi {

    @GET("getallmakes?format=json")
    suspend fun getMakeList():Response<CarMakeListRespose>
}