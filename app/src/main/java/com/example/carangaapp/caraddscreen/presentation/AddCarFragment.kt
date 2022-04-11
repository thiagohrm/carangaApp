package com.example.carangaapp.caraddscreen.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.get
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.carangaapp.R
import com.example.carangaapp.caraddscreen.presentation.CarMakeListViewModel.*
import com.example.carangaapp.caraddscreen.presentation.CarModelListViewModel.CarModelsListEvents
import com.example.carangaapp.mainscreen.domain.model.CarModel
import com.example.carangaapp.mainscreen.domain.model.FuelTypeModel
import com.example.carangaapp.mainscreen.domain.model.FuelTypeModel.*
import com.example.carangaapp.mainscreen.presentation.CarViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import java.lang.Exception

@AndroidEntryPoint
class AddCarFragment : Fragment() {

    private val TAG = this::class.qualifiedName
    private val carViewModel: CarViewModel by viewModels()
//    private val carMakesListViewModel: CarMakeListViewModel by viewModels()
//    private val carModelListViewModel: CarModelListViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        Log.i(TAG, "OnCreate")
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_add_car, container, false)

        val edMake =
            rootView.findViewById<EditText>(R.id.actvr_make)
        val edModel =
            rootView.findViewById<EditText>(R.id.actv_model)
        val edYear = rootView.findViewById<EditText>(R.id.spinner_year)
        val mSpinner = rootView.findViewById<Spinner>(R.id.spinner_fueltype)
        val edPlate = rootView.findViewById<EditText>(R.id.edPlate)
        val btnAdd = rootView.findViewById<Button>(R.id.btn_add)


        setupSpinner(mSpinner)

//        populateMakeList(makeAutoCompleteTextView)
//        populateModelsList(modelAutocompleteTextView)



        btnAdd.setOnClickListener {
            try {
                carViewModel.insertCar(
                    listOf(
                        CarModel(
                            model = edModel.text.toString(),
                            make = edMake.text.toString(),
                            plate = edPlate.text.toString(),
                            year = edYear.text.toString().toInt(),
                            fuelType = returnFuelType(mSpinner)
                        )
                    )
                )
            } catch (e: Exception) {
                Log.i(TAG, e.toString())
                Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show()
            }
            findNavController().navigate(R.id.principalFragment)
        }

        return rootView
    }

    private fun setupSpinner(mSpinner: Spinner){
        val mArray = FuelTypeModel.values().map {
            it.name
        }.toTypedArray()

        val arrayAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            mArray
        )
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mSpinner.adapter = arrayAdapter
    }

    private fun returnFuelType(mSpinner: Spinner) : FuelTypeModel{
        val returnFuelTypeModel: FuelTypeModel = when (mSpinner.selectedItem.toString()) {
            "DIESEL" -> DIESEL
            "ETHANOL" -> ETHANOl
            "FLEX" -> FLEX
            else -> {
                GASOLINE
            }
        }
        return returnFuelTypeModel
    }

//    private fun populateMakeList(mAutoCompleteTextView: AutoCompleteTextView) {
//        Log.i(TAG, "populateMakeList($mAutoCompleteTextView)")
//
//        carMakesListViewModel.getListFromApi()
//
//        lifecycleScope.launchWhenCreated {
//            carMakesListViewModel.makesListEvent.collect { event ->
//                when (event) {
//                    is CarMakesListEvent.Loading -> {
//                        Log.i(TAG, "CarMakesListEvent.Loading")
//                    }
//                    is CarMakesListEvent.Empty -> {
//                        Log.i(TAG, "Empty")
//                    }
//                    is CarMakesListEvent.Success -> {
//                        Log.i(TAG, "CarMakesListEvent.Success")
//
//                        val array = event.resultList.map {
//                            it.makeName
//                        }.toTypedArray()
//
//                        array.sortBy {
//                            it
//                        }
//
////                        setupSpinner(mAutoCompleteTextView, array)
//                        setupEditText(mAutoCompleteTextView, array)
//                    }
//                    is CarMakesListEvent.Error -> {
//                        Log.i(TAG, event.errorText)
//                    }
//                    else -> Unit
//                }
//            }
//        }
//    }
//
//
//    private fun populateModelsList(mAutoCompleteTextView: AutoCompleteTextView) {
//        Log.i(TAG, "populateModelsList($mAutoCompleteTextView)")
//
//        carModelListViewModel.getListFromApi("FORD")
//
//        lifecycleScope.launchWhenCreated {
//            carModelListViewModel.modelsList.collect { event ->
//                when (event) {
//                    is CarModelsListEvents.Loading -> {
//                        Log.i(TAG, "CarModelsListEvents.Loading")
//                    }
//                    is CarModelsListEvents.Empty -> {
//                        Log.i(TAG, "Empty")
//                    }
//                    is CarModelsListEvents.Success -> {
//                        Log.i(TAG, "CarModelsListEvents.Success")
//
//                        val array = event.resultList.map {
//                            it.modelName
//                        }.toTypedArray()
//
//                        array.sortBy {
//                            it
//                        }
//
//                        setupEditText(mAutoCompleteTextView, array)
//                    }
//                    is CarModelsListEvents.Error -> {
//                        Log.i(TAG, event.errorText)
//                    }
//                    else -> Unit
//                }
//            }
//        }
//
//
//    }


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

//    private fun setupEditText(mText: AutoCompleteTextView, mArray: Array<String>) {
//        Log.i(TAG, "setupEditText($mText , $mArray)")
//        val arrayAdapter = ArrayAdapter(
//            requireContext(),
//            android.R.layout.simple_list_item_1,
//            mArray
//        )
//        mText.setAdapter(arrayAdapter)
//        mText.isEnabled = true
//    }

}