package com.example.carangaapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carangaapp.data.models.carmake.CarMakesListResult
import com.example.carangaapp.data.repository.carmake.CarMakeListRepoImpl
import com.example.carangaapp.utils.DispatcherUtils
import com.example.carangaapp.utils.ResourceUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarMakeListViewModel @Inject constructor(
    private val repository: CarMakeListRepoImpl,
    private val dispatchers: DispatcherUtils,
) : ViewModel() {

    private val TAG = this::class.qualifiedName

    sealed class CarMakesListEvent {
        class Success(val resultList: List<CarMakesListResult>) : CarMakesListEvent()
        class Error(val errorText: String) : CarMakesListEvent()
        object Loading : CarMakesListEvent()
        object Empty : CarMakesListEvent()
    }

    private var _makesListEvent = MutableStateFlow<CarMakesListEvent>(CarMakesListEvent.Empty)
    val makesListEvent : StateFlow<CarMakesListEvent> = _makesListEvent

    fun getListFromApi(){

        Log.i(TAG,"getListFromApi()")

        _makesListEvent.value = CarMakesListEvent.Loading

        viewModelScope.launch(dispatchers.io) {
            when (val makesListResponse = repository.getMakesList()) {
                is ResourceUtils.Success -> {
                    val makes = makesListResponse.data!!.results
                    _makesListEvent.value = CarMakesListEvent.Success(makes)
                }
                is ResourceUtils.Error -> {
                    _makesListEvent.value = CarMakesListEvent.Error(makesListResponse.message!!)
                }
            }
        }
    }
}


