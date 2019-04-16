package com.ktu.nearfuel.ui.main.presenter

import android.util.Log
import com.google.android.gms.maps.model.LatLng
import com.ktu.nearfuel.ui.main.view.MainMVPView


interface MapsNewContract<V : MainMVPView> {

    fun getStationsNearLocation(latLng:LatLng)

}