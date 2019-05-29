package com.ktu.nearfuel.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.ktu.components.objects.GasStation
import com.ktu.nearfuel.R
import kotlinx.android.synthetic.main.station_info_window.view.*

class StationInfoWindowAdapter(private val mContext: Context) : GoogleMap.InfoWindowAdapter {

    private var mWindow : View = LayoutInflater.from(mContext).inflate(R.layout.station_info_window, null)

    private fun renderWindowText(marker : Marker, view : View){
        val station : GasStation = marker.tag as GasStation
        view.iw_title.text = station.title
        view.iw_address.text = station.address
        val resources = mContext.resources
        if(station.diesel_price==null)
        {
            view.iw_diesel_price.text = resources.getString(R.string.iw_diesel, "No data")
        }
        else
        view.iw_diesel_price.text = resources.getString(R.string.iw_diesel, station.diesel_price)
        if(station.fuel_price==null)
            view.iw_fuel_price.text = resources.getString(R.string.iw_petrol, "No data")
        else
        view.iw_fuel_price.text = resources.getString(R.string.iw_petrol, station.fuel_price)
        if(station.gas_price==null)
            view.iw_gas_price.text = resources.getString(R.string.iw_gas, "No data")
        else
        view.iw_gas_price.text = resources.getString(R.string.iw_gas, station.gas_price)
        view.iw_updated_at.text = resources.getString(R.string.iw_updated_at, station.updated_at)
    }

    override fun getInfoContents(marker: Marker?): View {
        if(marker != null){
            renderWindowText(marker, mWindow)
        }
        return mWindow
    }

    override fun getInfoWindow(marker: Marker?): View {
        if(marker != null){
            renderWindowText(marker, mWindow)
        }
        return mWindow
    }
}