package com.example.carangaapp.caraddscreen.presentation

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
import com.example.carangaapp.mainscreen.data.local.CarEntity
import com.example.carangaapp.mainscreen.domain.model.FuelTypeModel.DIESEL
import com.example.carangaapp.caraddscreen.presentation.CarMakeListViewModel.*
import com.example.carangaapp.caraddscreen.presentation.CarModelListViewModel.CarModelsListEvents
import com.example.carangaapp.mainscreen.presentation.CarViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import java.lang.Exception

@AndroidEntryPoint
class AddCarFragment : Fragment() {

    private val TAG = this::class.qualifiedName
    private val carViewModel: CarViewModel by viewModels()
    private val carMakesListViewModel: CarMakeListViewModel by viewModels()
    private val carModelListViewModel: CarModelListViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        Log.i(TAG, "OnCreate")
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_add_car, container, false)

        val makeAutoCompleteTextView =
            rootView.findViewById<AutoCompleteTextView>(R.id.actvr_make)
        val modelAutocompleteTextView =
            rootView.findViewById<AutoCompleteTextView>(R.id.actv_model)
        val spinerYear = rootView.findViewById<Spinner>(R.id.spinner_year)
        val spinerFuelType = rootView.findViewById<Spinner>(R.id.spinner_fueltype)
        val edPlate = rootView.findViewById<EditText>(R.id.edPlate)
        makeAutoCompleteTextView.isEnabled = false
        modelAutocompleteTextView.isEnabled = false
        spinerYear.isEnabled = false
        spinerFuelType.isEnabled = false
        edPlate.isEnabled = false

        populateMakeList(makeAutoCompleteTextView)
        populateModelsList(modelAutocompleteTextView)

        val btnAdd = rootView.findViewById<FloatingActionButton>(R.id.btn_add)

        btnAdd.setOnClickListener {
            try {
                carViewModel.insertCar(
                    CarEntity(
                        model = modelAutocompleteTextView.text.toString(),
                        make = makeAutoCompleteTextView.text.toString(),
                        plate = edPlate.text.toString(),
                        year = spinerYear.selectedItem.toString().toInt(),
                        fuelType = DIESEL
                    )
                )
            } catch (e: Exception) {
                Log.i(TAG, e.toString())
                Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show()
            }
        }

        return rootView
    }

    private fun populateMakeList(mAutoCompleteTextView: AutoCompleteTextView) {
        Log.i(TAG, "populateMakeList($mAutoCompleteTextView)")

        carMakesListViewModel.getListFromApi()

        lifecycleScope.launchWhenCreated {
            carMakesListViewModel.makesListEvent.collect { event ->
                when (event) {
                    is CarMakesListEvent.Loading -> {
                        Log.i(TAG, "CarMakesListEvent.Loading")
                    }
                    is CarMakesListEvent.Empty -> {
                        Log.i(TAG, "Empty")
                    }
                    is CarMakesListEvent.Success -> {
                        Log.i(TAG, "CarMakesListEvent.Success")

                        val array = event.resultList.map {
                            it.makeName
                        }.toTypedArray()

                        array.sortBy {
                            it
                        }

//                        setupSpinner(mAutoCompleteTextView, array)
                        setupEditText(mAutoCompleteTextView, array)
                    }
                    is CarMakesListEvent.Error -> {
                        Log.i(TAG, event.errorText)
                    }
                    else -> Unit
                }
            }
        }
    }


    private fun populateModelsList(mAutoCompleteTextView: AutoCompleteTextView) {
        Log.i(TAG, "populateModelsList($mAutoCompleteTextView)")

        carModelListViewModel.getListFromApi("FORD")

        lifecycleScope.launchWhenCreated {
            carModelListViewModel.modelsList.collect { event ->
                when (event) {
                    is CarModelsListEvents.Loading -> {
                        Log.i(TAG, "CarModelsListEvents.Loading")
                    }
                    is CarModelsListEvents.Empty -> {
                        Log.i(TAG, "Empty")
                    }
                    is CarModelsListEvents.Success -> {
                        Log.i(TAG, "CarModelsListEvents.Success")

                        val array = event.resultList.map {
                            it.modelName
                        }.toTypedArray()

                        array.sortBy {
                            it
                        }

                        setupEditText(mAutoCompleteTextView, array)
                    }
                    is CarModelsListEvents.Error -> {
                        Log.i(TAG, event.errorText)
                    }
                    else -> Unit
                }
            }
        }


    }


//    private fun setupSpinner(mSpinner : Spinner,mArray : Array<String>){
//        Log.i(TAG,"setupSpinner($mSpinner , $mArray)")
//        val arrayAdapter = ArrayAdapter(
//            requireContext(),
//            android.R.layout.simple_spinner_item,
//            mArray
//        )
//        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        mSpinner.adapter = arrayAdapter
//        mSpinner.isEnabled = true
//    }

    private fun setupEditText(mText: AutoCompleteTextView, mArray: Array<String>) {
        Log.i(TAG, "setupEditText($mText , $mArray)")
        val arrayAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            mArray
        )
        mText.setAdapter(arrayAdapter)
        mText.isEnabled = true
    }

}