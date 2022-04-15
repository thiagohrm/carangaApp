package com.example.carangaapp.mainscreen.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.carangaapp.R
import com.example.carangaapp.mainscreen.domain.model.CarModel

class MainListItemAdapter(
    private val carList: List<CarModel>,
) : RecyclerView.Adapter<MainListItemAdapter.ItemViewHolder>() {

    private val TAG = MainListItemAdapter::class.qualifiedName

    open class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val carModelItem: TextView = view.findViewById<TextView>(R.id.tvItemModel)
        val carPlateItem: TextView = view.findViewById<TextView>(R.id.tvItemPlate)
        val carMakeItem: TextView = view.findViewById<TextView>(R.id.tvItemMake)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        Log.i(TAG, "onCreateViewHolder( parent=$parent, viewType=$viewType )")
        val holderLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_item, parent, false)
        return ItemViewHolder(holderLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        Log.i(TAG, "onBindViewHolder( holder=$holder, position=$position )")
        val item = carList[position]
        holder.carModelItem.text = item.model
        holder.carMakeItem.text = item.make
        holder.carPlateItem.text = item.plate

        holder.itemView.setOnClickListener {
            val bundle = bundleOf()
            bundle.putString("carModel",item.model)
            bundle.putString("carMake",item.make)
            bundle.putString("carPlate",item.plate)
            bundle.putInt("carId",item.id)

            findNavController(it).navigate(R.id.refuelFragment, bundle)
        }
    }

    override fun getItemCount(): Int {
        Log.i(TAG, "getItemCount()")
        return carList.size
    }


}