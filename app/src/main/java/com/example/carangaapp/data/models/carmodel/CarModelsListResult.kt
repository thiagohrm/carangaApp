package com.example.carangaapp.data.models.carmodel

import com.google.gson.annotations.SerializedName

data class CarModelsListResult(
    @SerializedName("Make_ID")
    val makeID: Int,

    @SerializedName("Make_Name")
    val makeName: String,

    @SerializedName("Model_ID")
    val modelID: Int,

    @SerializedName("Model_Name")
    val modelName: String
)