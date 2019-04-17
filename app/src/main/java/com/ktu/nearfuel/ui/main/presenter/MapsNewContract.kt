package com.ktu.nearfuel.ui.main.presenter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.LatLng
import com.ktu.components.objects.GasStation
import com.ktu.nearfuel.ui.main.view.MainMVPView


interface MapsNewContract<V : MainMVPView> {

    fun getStationsNearLocation(latLng:LatLng)
    fun getGasStationsLivedata(): MutableLiveData<List<GasStation>>

}