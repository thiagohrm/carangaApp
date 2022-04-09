package com.example.carangaapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.carangaapp.R
import com.example.carangaapp.adapter.MainListItemAdapter
import com.example.carangaapp.viewmodel.CarViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val TAG = MainFragment::class.qualifiedName
    private val carViewModel: CarViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        Log.i(TAG, "onCreate")
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_main, container, false)

        val fabIcon = rootView.findViewById<FloatingActionButton>(R.id.fab_add)
        val rv = rootView.findViewById<RecyclerView>(R.id.rvListaFragmentoPrincipal)

        carViewModel.carList.observe(viewLifecycleOwner) {
            rv.adapter = MainListItemAdapter(it)
        }

        fabIcon.setOnClickListener {
            findNavController().navigate(R.id.addCarFragment)
        }

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i(TAG, "onViewCreated()")
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        Log.i(TAG, "onViewStateRestored()")
        super.onViewStateRestored(savedInstanceState)
    }

    override fun onStart() {
        Log.i(TAG, "onStart()")
        super.onStart()
    }

    override fun onResume() {
        Log.i(TAG, "onResume()")
        super.onResume()
    }

    override fun onPause() {
        Log.i(TAG, "onPause()")
        super.onPause()
    }

    override fun onStop() {
        Log.i(TAG, "onStop()")
        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.i(TAG, "onSaveInstanceState()")
        super.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        Log.i(TAG, "onDestroyView()")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.i(TAG, "onDestroy()")
        super.onDestroy()
    }
}