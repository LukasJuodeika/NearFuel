package com.ktu.nearfuel.components

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ktu.components.objects.GasStation
import com.ktu.nearfuel.R
import kotlinx.android.synthetic.main.gas_station_list_item.view.*

class ListAdapter(private val list: List<GasStation>) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.gas_station_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(list[position])
    }


    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(item: GasStation) {
            view.list_item_price.text = item.price
            view.list_item_distance.text = item.distance.toString()
            view.list_item_title.text = item.title
        }
    }

}