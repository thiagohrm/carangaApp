package com.example.carangaapp.data.models.carmodel

import com.google.gson.annotations.SerializedName

data class CarModelsListResponse(

    @SerializedName("Count")
    val count: Int,


    @SerializedName("Message")
    val message: String,


    @SerializedName("Results")
    val results: List<CarModelsListResult>,


    @SerializedName("SearchCriteria")
    val searchCriteria: String
)