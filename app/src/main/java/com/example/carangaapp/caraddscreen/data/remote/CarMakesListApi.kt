package com.example.carangaapp.caraddscreen.data.remote

import com.example.carangaapp.caraddscreen.domain.model.carmake.CarMakeListResponse
import retrofit2.Response
import retrofit2.http.GET

interface CarMakesListApi {

    @GET("getallmakes?format=json")
    suspend fun getMakeList():Response<CarMakeListResponse>
}