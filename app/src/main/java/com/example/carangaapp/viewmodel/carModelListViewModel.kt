package com.example.carangaapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carangaapp.data.models.carmake.CarMakesListResult
import com.example.carangaapp.data.models.carmodel.CarModelsListResponse
import com.example.carangaapp.data.models.carmodel.CarModelsListResult
import com.example.carangaapp.data.repository.carmodel.CarModelListRepoImpl
import com.example.carangaapp.utils.DispatcherUtils
import com.example.carangaapp.utils.ResourceUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarModelListViewModel @Inject constructor(
    private val repository: CarModelListRepoImpl,
    private val dispatchers: DispatcherUtils,
) : ViewModel() {

    private val TAG = this::class.qualifiedName

    sealed class CarModelsListEvents {
        class Success(val resultList: List<CarModelsListResult>) : CarModelsListEvents()
        class Error(val errorText: String) : CarModelsListEvents()
        object Loading : CarModelsListEvents()
        object Empty : CarModelsListEvents()
    }

    private var _modelsList = MutableStateFlow<CarModelsListEvents>(
        CarModelsListEvents.Empty)
    val modelsList: StateFlow<CarModelsListEvents> = _modelsList

    fun getListFromApi(make : String) {

        Log.i(TAG, "getListFromApi()")
        repository.make = make

        _modelsList.value = CarModelsListEvents.Loading

        viewModelScope.launch(dispatchers.io) {
            when (val modelsListResponse = repository.getModelsList()) {
                is ResourceUtils.Success -> {
                    val makes = modelsListResponse.data!!.results
                    _modelsList.value = CarModelsListEvents.Success(makes)
                }
                is ResourceUtils.Error -> {
                    _modelsList.value =
                        CarModelsListEvents.Error(modelsListResponse.message!!)
                }
            }
        }
    }
}