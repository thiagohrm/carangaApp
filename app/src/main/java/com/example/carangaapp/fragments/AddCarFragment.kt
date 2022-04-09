package com.example.carangaapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.viewModels
import com.example.carangaapp.R
import com.example.carangaapp.data.models.CarModel
import com.example.carangaapp.data.models.FuelTypeModel.DIESEL
import com.example.carangaapp.data.CarMakesListApi
import com.example.carangaapp.utils.NetworkUtils
import com.example.carangaapp.viewmodel.CarViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

@AndroidEntryPoint
class AddCarFragment : Fragment() {

    private val TAG = MainFragment::class.qualifiedName
    private val carViewModel: CarViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_add_car, container, false)
        val textView = rootView.findViewById<TextView>(R.id.tvMakesList)
        getMakesList(textView)

        val spinerFuelType: Spinner = rootView.findViewById<Spinner>(R.id.spinner_fueltype)
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.models,
            android.R.layout.simple_spinner_item
        ).also { arrayAdapter ->
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinerFuelType.adapter = arrayAdapter
        }

        val btnAdd = rootView.findViewById<Button>(R.id.btn_add)
        btnAdd.setOnClickListener {
            try {
                carViewModel.insertCar(
                    CarModel(
                        model = "dummy",
                        make = "dummy",
                        plate = "dummy",
                        year = 123,
                        fuelType = DIESEL
                    )
                )
            } catch (e: Exception) {
                Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show()
            }
        }



        return rootView
    }

    private fun getMakesList(view : TextView){
        val retrofitClient = NetworkUtils.getRetrofitInstance("https://vpic.nhtsa.dot.gov/api/vehicles/")
        val endpoint = retrofitClient.create(CarMakesListApi::class.java)
        GlobalScope.launch {
            val temp = endpoint.getMakeList().body()?.results
            temp?.forEach {
                Log.i(TAG,it.makeName)
//                view.text = it.Make_Name
            }
        }
    }


}