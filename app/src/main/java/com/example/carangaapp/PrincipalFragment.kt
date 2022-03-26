package com.example.carangaapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class PrincipalFragment : Fragment() {

    private val TAG = PrincipalFragment::class.qualifiedName

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
        return inflater.inflate(R.layout.fragment_principal, container, false)
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