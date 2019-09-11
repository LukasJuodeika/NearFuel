package com.ktu.nearfuel.components

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ktu.components.data.FuelType
import com.ktu.components.objects.GasStation
import com.ktu.nearfuel.R
import kotlinx.android.synthetic.main.gas_station_list_item.view.*

class ListAdapter(
    private var list: List<GasStation>,
    private val type: FuelType,
    private val listener: OnListItemClickListener
) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.gas_station_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(list[position], type)
    }

    fun updateList(list: List<GasStation>) {
        this.list = list
        notifyDataSetChanged()
    }


    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(item: GasStation, type: FuelType) {
            setFuelPrice(item, type)
            //  view.list_item_distance.text = item.distance.toString()
            view.list_item_title.text = item.title
            setClickListeners(item)
        }

        private fun setClickListeners(item: GasStation) {
            view.setOnClickListener {
                listener.onItemClick(item)
            }
            view.img_maps.setOnClickListener {
                listener.onMapClick(item)
            }
            view.setOnLongClickListener {
                listener.onItemLongClick(item)
            }
        }

        private fun setFuelPrice(item: GasStation, type: FuelType) {
            when (type) {
                FuelType.DIESEL -> view.list_item_price.text = item.diesel_price
                FuelType.PETROL -> view.list_item_price.text = item.fuel_price
                FuelType.GAS -> view.list_item_price.text = item.gas_price
                else -> view.list_item_price.text = "0"
            }
        }
    }


    interface OnListItemClickListener {
        fun onItemClick(item: GasStation)
        fun onItemLongClick(item: GasStation): Boolean
        fun onMapClick(item: GasStation)
    }
}