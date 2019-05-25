package com.ktu.nearfuel.maps.contracts

import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.LatLng
import com.ktu.components.objects.GasStation
import com.ktu.nearfuel.network.Resource
import com.ktu.nearfuel.maps.views.MainMVPView


interface MapsNewContract<V : MainMVPView> {

    fun getStationsNearLocation(latLng:LatLng)
    fun getGasStationsLivedata(): MutableLiveData<List<GasStation>>
    fun getGasStationUpdateResult(): MutableLiveData<Resource<GasStation>>
    fun updateGasStation(gasStation: GasStation)

}