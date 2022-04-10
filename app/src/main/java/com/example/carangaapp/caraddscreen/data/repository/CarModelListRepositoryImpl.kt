package com.example.carangaapp.caraddscreen.data.repository

import android.util.Log
import com.example.carangaapp.caraddscreen.data.remote.CarModelsListApi
import com.example.carangaapp.caraddscreen.domain.model.carmodel.CarModelsListResponse
import com.example.carangaapp.caraddscreen.domain.repository.CarModelListRepository
import com.example.carangaapp.utils.ResourceUtils
import java.lang.Exception
import javax.inject.Inject

class CarModelListRepositoryImpl @Inject constructor(
    private val api : CarModelsListApi
) : CarModelListRepository {

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