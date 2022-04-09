package com.example.carangaapp.data.repository

import android.util.Log
import com.example.carangaapp.data.CarMakesListApi
import com.example.carangaapp.data.models.CarMakeListResponse
import com.example.carangaapp.utils.ResourceUtils
import java.lang.Exception
import javax.inject.Inject

class CarMakeListRepoImpl @Inject constructor(
    private val api: CarMakesListApi,
) : CarMakeListRepo {
    private val TAG = this::class.qualifiedName

    override suspend fun getMakesList(): ResourceUtils<CarMakeListResponse> {
        Log.i(TAG,"getMakesList()")

        return try {
            val response = api.getMakeList()
            val result = response.body()
            if (response.isSuccessful && result != null) {
                ResourceUtils.Success(result)
            } else {
                ResourceUtils.Error(response.message())
            }
        } catch (e: Exception) {
            ResourceUtils.Error(e.message ?: "Error Occurred check logs")
        }
    }
}