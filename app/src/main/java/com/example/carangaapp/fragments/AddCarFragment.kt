package com.example.carangaapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.carangaapp.R
import com.example.carangaapp.data.models.CarModel
import com.example.carangaapp.data.models.FuelTypeModel.DIESEL
import com.example.carangaapp.viewmodel.CarMakeListViewModel
import com.example.carangaapp.viewmodel.CarMakeListViewModel.*
import com.example.carangaapp.viewmodel.CarModelListViewModel
import com.example.carangaapp.viewmodel.CarModelListViewModel.CarModelsListEvents
import com.example.carangaapp.viewmodel.CarViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import java.lang.Exception

@AndroidEntryPoint
class AddCarFragment : Fragment() {

    private val TAG = this::class.qualifiedName
    private val carViewModel: CarViewModel by viewModels()
    private val carMakesListViewModel : CarMakeListViewModel by viewModels()
    private val carModelListViewModel : CarModelListViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        Log.i(TAG,"OnCreate")
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_add_car, container, false)

        val spinerMake: Spinner = rootView.findViewById<Spinner>(R.id.spinner_make)
        val spinerModel = rootView.findViewById<Spinner>(R.id.spinner_model)
        val spinerYear = rootView.findViewById<Spinner>(R.id.spinner_year)
        val spinerFuelType = rootView.findViewById<Spinner>(R.id.spinner_fueltype)
        val edPlate = rootView.findViewById<EditText>(R.id.edPlate)
        spinerMake.isEnabled = false
        spinerModel.isEnabled = false
        spinerYear.isEnabled = false
        spinerFuelType.isEnabled = false

        populateMakeList(spinerMake)
        populateModelsList(spinerModel)

        val btnAdd = rootView.findViewById<Button>(R.id.btn_add)

        btnAdd.setOnClickListener {
            try {
                carViewModel.insertCar(
                    CarModel(
                        model = spinerModel.selectedItem.toString(),
                        make = spinerMake.selectedItem.toString(),
                        plate = edPlate.text.toString(),
                        year = spinerYear.selectedItem.toString().toInt(),
                        fuelType = DIESEL
                    )
                )
            } catch (e: Exception) {
                Log.i(TAG,e.toString())
                Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show()
            }
        }

        return rootView
    }

    private fun populateMakeList(mSpinner : Spinner){
        Log.i(TAG,"populateMakeList($mSpinner)")

        carMakesListViewModel.getListFromApi()

        lifecycleScope.launchWhenCreated {
            carMakesListViewModel.makesListEvent.collect { event ->
                when(event){
                    is CarMakesListEvent.Loading -> {
                        Log.i(TAG,"Loading")
                    }
                    is CarMakesListEvent.Empty -> {
                        Log.i(TAG,"Empty")
                    }
                    is CarMakesListEvent.Success -> {
                        Log.i(TAG,"Success")

                        val array = event.resultList.map {
                            it.makeName
                        }.toTypedArray()

                        array.sortBy {
                            it
                        }

                        setupSpinner(mSpinner, array)
                    }
                    is CarMakesListEvent.Error -> {
                        Log.i(TAG,event.errorText)
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun setupSpinner(mSpinner : Spinner,mArray : Array<String>){
        Log.i(TAG,"setupSpinner($mSpinner , $mArray)")
        val arrayAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            mArray
        )
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mSpinner.adapter = arrayAdapter
        mSpinner.isEnabled = true
    }

    private fun populateModelsList(mSpinner: Spinner){
        Log.i(TAG,"populateModelsList($mSpinner)")

        carModelListViewModel.getListFromApi("FORD")

        lifecycleScope.launchWhenCreated {
            carModelListViewModel.modelsList.collect { event ->
                when(event){
                    is CarModelsListEvents.Loading -> {
                        Log.i(TAG,"Loading")
                    }
                    is CarModelsListEvents.Empty -> {
                        Log.i(TAG,"Empty")
                    }
                    is CarModelsListEvents.Success -> {
                        Log.i(TAG,"Success")

                        val array = event.resultList.map {
                            it.modelName
                        }.toTypedArray()

                        array.sortBy {
                            it
                        }

                        setupSpinner(mSpinner, array)
                    }
                    is CarModelsListEvents.Error -> {
                        Log.i(TAG,event.errorText)
                    }
                    else -> Unit
                }
            }
        }



    }

}