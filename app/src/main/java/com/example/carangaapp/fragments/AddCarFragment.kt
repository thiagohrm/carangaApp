package com.example.carangaapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.viewModels
import com.example.carangaapp.R
import com.example.carangaapp.data.CarModel
import com.example.carangaapp.data.FuelTypeModel.DIESEL
import com.example.carangaapp.viewmodel.CarViewModel
import dagger.hilt.android.AndroidEntryPoint
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


}