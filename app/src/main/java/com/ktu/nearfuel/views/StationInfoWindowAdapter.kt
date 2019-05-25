package com.ktu.nearfuel.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.ktu.components.objects.GasStation
import com.ktu.nearfuel.R
import kotlinx.android.synthetic.main.station_info_window.view.*

class StationInfoWindowAdapter(mContext: Context) : GoogleMap.InfoWindowAdapter {

    private var mWindow : View = LayoutInflater.from(mContext).inflate(R.layout.station_info_window, null)

    private fun renderWindowText(marker : Marker, view : View){
        val station : GasStation = marker.tag as GasStation
        view.iw_title.text = station.title
        view.iw_address.text = station.address
        view.iw_diesel_price.text = "Diesel: ${station.diesel_price}"
        view.iw_fuel_price.text = "Fuel: ${station.fuel_price}"
        view.iw_gas_price.text = "Gas: ${station.gas_price}"
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