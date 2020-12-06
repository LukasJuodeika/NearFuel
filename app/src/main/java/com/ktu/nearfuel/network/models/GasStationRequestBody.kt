package com.ktu.nearfuel.network.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ktu.components.objects.GasStation

class GasStationRequestBody(
    var address: String?,
    var created_at: String?,
    var diesel_price: String?,
    var fuelType: String?,
    var fuel_price: String?,
    var gas_price: String?,
    var lat: String,
    val lng: String,
    val station_id: Int?,
    val title: String?,
    val updated_at: String?,
    val user_id: Int?
//    ,
//    val uid: String
) {

}
