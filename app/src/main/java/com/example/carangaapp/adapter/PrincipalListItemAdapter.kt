package com.example.carangaapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.carangaapp.R

class PrincipalListItemAdapter(
    val lista: List<PrincipalDataClass>,
) : RecyclerView.Adapter<PrincipalListItemAdapter.ItemViewHolder>() {

    private val TAG = PrincipalListItemAdapter::class.qualifiedName

    open class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val carroItem: TextView = view.findViewById<TextView>(R.id.tvItemCarro)
        val placaItem: TextView = view.findViewById<TextView>(R.id.tvItemPlaca)
        val marcaItem: TextView = view.findViewById<TextView>(R.id.tvItemMarca)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        Log.i(TAG, "onCreateViewHolder( parent=$parent, viewType=$viewType )")
        val holderLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.principal_item, parent, false)
        return ItemViewHolder(holderLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        Log.i(TAG, "onBindViewHolder( holder=$holder, position=$position )")
        val item = lista[position]
        holder.carroItem.text = item.modelo
        holder.marcaItem.text = item.marca
        holder.placaItem.text = item.placa
    }

    override fun getItemCount(): Int {
        Log.i(TAG, "getItemCount()")
        return lista.size
    }


}