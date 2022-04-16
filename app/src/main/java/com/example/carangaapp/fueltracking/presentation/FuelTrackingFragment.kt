package com.example.carangaapp.fueltracking.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.carangaapp.R
import com.example.carangaapp.fueltracking.domain.model.FuelTracking
import com.example.carangaapp.mainscreen.domain.model.FuelTypeModel
import com.example.carangaapp.mainscreen.presentation.MainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FuelTrackingFragment : Fragment() {
    private val TAG = MainFragment::class.qualifiedName
    private val viewModel : FuelTrackingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_fueltracking, container, false)
        val btnSave = rootView.findViewById<Button>(R.id.btnAddRefuel)
        var carId : Int? = null

        arguments?.let {
            rootView.findViewById<TextView>(R.id.tvItemModel).text = it.getString("carModel")
            rootView.findViewById<TextView>(R.id.tvItemPlate).text = it.getString("carPlate")
            rootView.findViewById<TextView>(R.id.tvItemMake).text = it.getString("carMake")
            carId = it.getInt("carId")
        }

        btnSave.setOnClickListener {
            carId?.let {
                viewModel.insertFuelTrackingOnDb(
                    listOf(FuelTracking(
                        carId = it,
                        fuelType = FuelTypeModel.DIESEL,
                        odometer = rootView.findViewById<TextView>(R.id.edOdometer).text.toString().toInt(),
                        price = rootView.findViewById<TextView>(R.id.edPrice).text.toString().toFloat(),
                        amount = rootView.findViewById<TextView>(R.id.edAmount).text.toString().toFloat(),
                        volume = rootView.findViewById<TextView>(R.id.edVolume).text.toString().toFloat()
                    ))
                )
            }
            findNavController().navigate(R.id.principalFragment)
        }

        return rootView
    }
}

