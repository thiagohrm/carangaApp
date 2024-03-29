package com.example.carangaapp.caraddscreen.domain.model.carmake

import com.google.gson.annotations.SerializedName

data class CarMakeListResponse(
    @SerializedName("Count")
    val count: Int,

    @SerializedName("Message")
    val message: String,

    @SerializedName("Results")
    val results: List<CarMakesListResult>,

    @SerializedName("searchCriteria")
    val searchCriteria: Any
)