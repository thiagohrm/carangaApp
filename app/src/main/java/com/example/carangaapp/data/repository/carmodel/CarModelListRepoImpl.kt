package com.example.carangaapp.data.repository.carmodel

import android.util.Log
import com.example.carangaapp.data.interfaces.CarModelsListApi
import com.example.carangaapp.data.models.carmodel.CarModelsListResponse
import com.example.carangaapp.utils.ResourceUtils
import java.lang.Exception
import javax.inject.Inject

class CarModelListRepoImpl @Inject constructor(
    private val api : CarModelsListApi
) :CarModelListRepo {

    private val TAG = this::class.qualifiedName

    override suspend fun getModelsList(make : String): ResourceUtils<CarModelsListResponse> {
        Log.i(TAG,"getModelsList($make)")

        return try {
            val response = api.getModelsList(make)
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