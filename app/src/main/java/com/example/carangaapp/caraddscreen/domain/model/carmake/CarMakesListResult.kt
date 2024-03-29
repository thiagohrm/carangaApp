package com.example.carangaapp.caraddscreen.domain.model.carmake

import com.google.gson.annotations.SerializedName

data class CarMakesListResult(

    @SerializedName("Make_ID")
    val makeID: Int,

    @SerializedName("Make_Name")
    val makeName: String
)