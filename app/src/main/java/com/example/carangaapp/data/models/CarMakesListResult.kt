package com.example.carangaapp.data.models

import com.google.gson.annotations.SerializedName

data class CarMakesListResult(

    @SerializedName("Make_ID")
    val makeID: Int,

    @SerializedName("Make_Name")
    val makeName: String
)